package executor.service.service;

import executor.service.model.Scenario;
import executor.service.model.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DefaultScenarioExecutor implements ScenarioExecutor {
    private final ClickXpath clickXpath = new ClickXpath();
    private final ClickCss clickCss = new ClickCss();
    private final Sleep sleep = new Sleep();

    @Override
    public void execute(Scenario scenario, WebDriver webDriver) {
        webDriver.get(scenario.getSite());
        List<Step> steps = scenario.getSteps();
        for (Step step : steps) {
            if ("clickcss".equalsIgnoreCase(step.getAction())) {
                clickCss.step(webDriver, step);
            } else if ("clickXpath".equalsIgnoreCase(step.getAction())) {
                clickXpath.step(webDriver, step);
            } else if ("sleep".equalsIgnoreCase(step.getAction())) {
                sleep.step(webDriver, step);
            }
        }
        webDriver.quit();
    }
}
