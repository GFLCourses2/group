package executor.service.service.execution.executionservice;

import executor.service.model.ProxyCredentials;
import executor.service.model.Scenario;
import org.openqa.selenium.WebDriver;

public interface ScenarioExecutor {
    void execute(Scenario scenario, WebDriver webDriver) throws InterruptedException;
}
