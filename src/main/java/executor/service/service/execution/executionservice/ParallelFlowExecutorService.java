package executor.service.service.execution.executionservice;

import executor.service.config.ConfigHolder;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.service.listener.ScenarioSourceListener;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ParallelFlowExecutorService {

    private final ScenarioExecutor scenarioExecutor;
    private final ScenarioSourceListener scenarioListener;
    private final WebDriverFactory webDriverFactory;
    private final ThreadPoolExecutor threadPoolExecutor;
    private final Queue<Scenario> scenarioQueue = new LinkedBlockingQueue<>();

    public ParallelFlowExecutorService(ConfigHolder configHolder,
                                       ScenarioExecutor scenarioExecutor,
                                       ScenarioSourceListener scenarioListener,
                                       WebDriverFactory webDriverFactory) {
        this.scenarioExecutor = scenarioExecutor;
        this.scenarioListener = scenarioListener;
        this.webDriverFactory = webDriverFactory;
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(configHolder.getThreadPoolConfig().getCorePoolSize());
    }

    public void run() {
        addScenariosIntoQueue();

        try {
            for (int i = 0; i < scenarioQueue.size(); i++) {
                Scenario scenario = scenarioQueue.poll();
                Runnable runnable = () -> {
                    try {
                        scenarioExecutor.execute(scenario, webDriverFactory.create());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                };
                threadPoolExecutor.execute(runnable);
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private void addScenariosIntoQueue() {
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
    }
}