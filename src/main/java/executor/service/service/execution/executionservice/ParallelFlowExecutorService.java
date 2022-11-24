package executor.service.service.execution.executionservice;

import executor.service.config.ConfigHolder;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.service.listener.ScenarioSourceListener;
import org.openqa.selenium.WebDriver;

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

    public void run() throws InterruptedException {
        WebDriver webDriver = webDriverFactory.create();
        addScenariosIntoQueue();

        for (int i = 0; i < scenarioQueue.size(); i++) {
            Scenario scenario = scenarioQueue.poll();
            threadPoolExecutor.execute(() -> {
                try {
                    scenarioExecutor.execute(scenario, webDriver);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        threadPoolExecutor.shutdown();
    }

    private void addScenariosIntoQueue() throws InterruptedException {
        Runnable worker = () -> {
            try {
                scenarioListener.appendScenarios(scenarioQueue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread appenderScenarios = new Thread(worker);
        appenderScenarios.setName("Append new scenarios");
        appenderScenarios.start();
        appenderScenarios.join();
    }
}