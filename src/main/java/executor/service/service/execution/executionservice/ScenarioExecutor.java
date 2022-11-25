package executor.service.service.execution.executionservice;

import executor.service.model.Scenario;
import org.openqa.selenium.WebDriver;

public interface ScenarioExecutor {
    void execute(Scenario scenario, WebDriver webDriver, Runnable callback) throws InterruptedException;
}
