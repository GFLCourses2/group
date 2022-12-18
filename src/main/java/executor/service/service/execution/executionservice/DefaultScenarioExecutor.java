package executor.service.service.execution.executionservice;

import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.ClickCss;
import executor.service.service.execution.stepexecution.ClickXpath;
import executor.service.service.execution.stepexecution.Sleep;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultScenarioExecutor implements ScenarioExecutor {
    private final ClickXpath clickXpath;
    private final ClickCss clickCss;
    private final Sleep sleep;

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
