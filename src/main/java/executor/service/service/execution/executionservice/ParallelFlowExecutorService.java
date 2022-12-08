package executor.service.service.execution.executionservice;

import executor.service.config.ConfigHolder;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.service.execution.executionservice.worker.Worker;
import executor.service.service.listener.ScenarioSourceListener;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ParallelFlowExecutorService {

    private final ScenarioExecutor scenarioExecutor;
    private final ScenarioSourceListener scenarioListener;
    private final WebDriverFactory webDriverFactory;
    private ThreadPoolExecutor threadPoolExecutor;

    private CountDownLatch countDownLatch;
    private final Queue<Scenario> scenarioQueue = new LinkedBlockingQueue<>();

    public ParallelFlowExecutorService(ScenarioExecutor scenarioExecutor,
                                       ScenarioSourceListener scenarioListener,
                                       WebDriverFactory webDriverFactory) {
        this.scenarioExecutor = scenarioExecutor;
        this.scenarioListener = scenarioListener;
        this.webDriverFactory = webDriverFactory;
    }

    @PostConstruct
    private void postConstruct(ConfigHolder configHolder){
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(configHolder.getThreadPoolConfig().getCorePoolSize());
        countDownLatch = new CountDownLatch(configHolder.getThreadPoolConfig().getCorePoolSize() + 1);
    }

    public void run() {
        threadPoolExecutor.execute(this::startListener);
        startWorkers(threadPoolExecutor);
        threadPoolExecutor.shutdown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void startWorkers(ThreadPoolExecutor threadPoolExecutor) {
        for (int i = 0; i < threadPoolExecutor.getCorePoolSize(); i++) {
            threadPoolExecutor.execute(new Worker(scenarioQueue, scenarioExecutor, webDriverFactory, countDownLatch));
        }
    }


    private void startListener() {
        Runnable worker = () -> {
            try {
                scenarioListener.appendScenarios(scenarioQueue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(worker);
        thread.setName("Appender scenarios");
        thread.start();
        countDownLatch.countDown();
    }
}