package executor.service.service.execution.executionservice.worker;

import executor.service.model.Scenario;
import executor.service.service.execution.executionservice.ScenarioExecutor;
import executor.service.service.holder.scenario.ScenarioHolder;
import org.openqa.selenium.WebDriver;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class Worker extends Thread {

    private final ScenarioHolder scenarioHolder;

    private final ScenarioExecutor scenarioExecutor;

    private final CountDownLatch countDownLatch;
    private boolean exit = false;

    public Worker(ScenarioHolder scenarioHolder, ScenarioExecutor scenarioExecutor,
                  CountDownLatch countDownLatch) {
        this.scenarioHolder = scenarioHolder;
        this.scenarioExecutor = scenarioExecutor;
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
                scenarioExecutor.execute(scenario, getWebDriver());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        countDownLatch.countDown();
    }

    protected abstract WebDriver getWebDriver();


    @Override
    public void interrupt() {
        this.exit = true;
    }
}
