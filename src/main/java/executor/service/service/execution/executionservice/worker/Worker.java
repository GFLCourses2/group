package executor.service.service.execution.executionservice.worker;

import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.service.execution.executionservice.ScenarioExecutor;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {

    private final Queue<Scenario> scenarioQueue;

    private final ScenarioExecutor scenarioExecutor;

    private final WebDriverFactory webDriverFactory;

    private final CountDownLatch countDownLatch;
    private boolean exit = false;

    public Worker(Queue<Scenario> scenarioQueue, ScenarioExecutor scenarioExecutor,
                  WebDriverFactory webDriverFactory, CountDownLatch countDownLatch) {
        this.scenarioQueue = scenarioQueue;
        this.scenarioExecutor = scenarioExecutor;
        this.webDriverFactory = webDriverFactory;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        while (!exit) {
            try {
                Scenario scenario = scenarioQueue.poll();
                if (scenario == null) {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    continue;
                }
                scenarioExecutor.execute(scenario, webDriverFactory.create(),null);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        countDownLatch.countDown();
    }


    @Override
    public void interrupt() {
        this.exit = true;
    }
}
