package executor.service.service.execution.executionservice;

import executor.service.config.ThreadPoolConfig;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.service.execution.executionservice.worker.Worker;
import executor.service.service.holder.ScenarioHolder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ParallelFlowExecutorService {

    private final ScenarioExecutor scenarioExecutor;
    private final WebDriverFactory webDriverFactory;
    private final ThreadPoolConfig threadPoolConfig;
    private final ScenarioHolder scenarioHolder;
    private ThreadPoolExecutor threadPoolExecutor;

    private CountDownLatch countDownLatch;

    public ParallelFlowExecutorService(ScenarioExecutor scenarioExecutor,
                                       ScenarioHolder scenarioHolder,
                                       ThreadPoolConfig threadPoolConfig,
                                       @Qualifier("webDriverFactoryProxy") WebDriverFactory webDriverFactory) {
        this.scenarioExecutor = scenarioExecutor;
        this.scenarioHolder = scenarioHolder;
        this.webDriverFactory = webDriverFactory;
        this.threadPoolConfig = threadPoolConfig;
    }

    @PostConstruct
    private void postConstruct() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolConfig.getCorePoolSize());
        countDownLatch = new CountDownLatch(threadPoolConfig.getCorePoolSize() + 1);
    }

    public void run() {
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
            threadPoolExecutor.execute(new Worker(
                    scenarioHolder,
                    scenarioExecutor,
                    webDriverFactory,
                    countDownLatch));
        }
    }
}