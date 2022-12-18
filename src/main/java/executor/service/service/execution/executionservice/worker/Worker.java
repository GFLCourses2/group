package executor.service.service.execution.executionservice.worker;

import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.service.execution.executionservice.ScenarioExecutor;
import executor.service.service.holder.ScenarioHolder;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {

    private final ScenarioHolder scenarioHolder;

    private final ScenarioExecutor scenarioExecutor;

    private final WebDriverFactory webDriverFactory;

    private final CountDownLatch countDownLatch;
    private boolean exit = false;

    public Worker(ScenarioHolder scenarioHolder, ScenarioExecutor scenarioExecutor,
                  WebDriverFactory webDriverFactory, CountDownLatch countDownLatch) {
        this.scenarioHolder = scenarioHolder;
        this.scenarioExecutor = scenarioExecutor;
        this.webDriverFactory = webDriverFactory;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                Optional<Scenario> scenarioOptional = scenarioHolder.getScenario();
                if (!scenarioOptional.isPresent()) {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    continue;
                }
                Scenario scenario = scenarioOptional.get();
                scenarioExecutor.execute(scenario, webDriverFactory.create());
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
