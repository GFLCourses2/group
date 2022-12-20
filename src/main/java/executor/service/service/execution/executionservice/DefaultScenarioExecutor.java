package executor.service.service.execution.executionservice;

import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.StepExecution;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultScenarioExecutor implements ScenarioExecutor {
    private final StepExecution clickCss;
    private final StepExecution clickXpath;
    private final StepExecution sleep;

    public DefaultScenarioExecutor(@Qualifier("ClickCss") StepExecution clickCss,
                                   @Qualifier("ClickXpath") StepExecution clickXpath,
                                   @Qualifier("Sleep") StepExecution sleep) {
        this.clickCss = clickCss;
        this.clickXpath = clickXpath;
        this.sleep = sleep;
    }

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        executeWithCallback(scenario, webDriver, null);
    }

    public void executeWithCallback(Scenario scenario, WebDriver webDriver, Runnable callback) {
        try {
            webDriver.get(scenario.getSite());
            List<Step> steps = scenario.getSteps();
            for (Step step : steps) {
                if (step.getAction().equalsIgnoreCase("clickcss")) {
                    if (callback != null) {
                        callback.run();
                    }
                    clickCss.step(webDriver, step);

                } else if (step.getAction().equalsIgnoreCase("clickXpath")) {
                    if (callback != null) {
                        callback.run();
                    }
                    clickXpath.step(webDriver, step);
                } else if (step.getAction().equalsIgnoreCase("sleep")) {
                    if (callback != null) {
                        callback.run();
                    }
                    sleep.step(webDriver, step);
                }
            }
        } finally {
            webDriver.quit();
        }


    }
}
