package executor.service.service.execution.executionservice;

import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.ClickCss;
import executor.service.service.execution.stepexecution.ClickXpath;
import executor.service.service.execution.stepexecution.Sleep;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DefaultScenarioExecutor implements ScenarioExecutor {
    private final ClickXpath clickXpath;
    private final ClickCss clickCss;
    private final Sleep sleep;


    public DefaultScenarioExecutor(ClickXpath clickXpath, ClickCss clickCss, Sleep sleep) {
        this.clickXpath = clickXpath;
        this.clickCss = clickCss;
        this.sleep = sleep;
    }

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        executeWithCallback(scenario, webDriver, null);
    }

    public void executeWithCallback(Scenario scenario, WebDriver webDriver, Runnable callback) {
        webDriver.get(scenario.getSite());
        List<Step> steps = scenario.getSteps();
        for (Step step : steps) {
            if ("clickcss".equalsIgnoreCase(step.getAction())) {
                if (callback != null) {
                    callback.run();
                }
                clickCss.step(webDriver, step);
            } else if ("clickXpath".equalsIgnoreCase(step.getAction())) {
                if (callback != null) {
                    callback.run();
                }
                clickXpath.step(webDriver, step);
            } else if ("sleep".equalsIgnoreCase(step.getAction())) {
                if (callback != null) {
                    callback.run();
                }
                sleep.step(webDriver, step);
            }
        }
        webDriver.quit();
    }
}
