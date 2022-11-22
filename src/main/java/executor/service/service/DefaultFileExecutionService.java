package executor.service.service;

import executor.service.model.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DefaultFileExecutionService implements FileExecutionService {
    private final WebDriver webDriver;
    private final ScenarioFileListener fileListener;
    private final ScenarioExecutor scenarioExecutor;

    public DefaultFileExecutionService(WebDriver webDriver,
                                       ScenarioFileListener fileListener,
                                       ScenarioExecutor scenarioExecutor) {
        this.webDriver = webDriver;
        this.fileListener = fileListener;
        this.scenarioExecutor = scenarioExecutor;
    }

    @Override
    public void execute(File source) throws IOException, InterruptedException {
        Collection<Scenario> scenarios = fileListener.getScenarios(source);

        for (Scenario s : scenarios) {
            scenarioExecutor.execute(s, webDriver);
        }
    }
}
