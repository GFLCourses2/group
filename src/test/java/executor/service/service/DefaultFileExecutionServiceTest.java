package executor.service.service;

import executor.service.factory.WebDriverInitializer;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultFileExecutionServiceTest {

    private static final File testFile = new File("testScenarios.json");

    private WebDriver driver;
    private ScenarioFileListener fileListener;
    private ScenarioExecutor scenarioExecutor;

    private DefaultFileExecutionService executionService;

    @Before
    public void init() {
        driver = new WebDriverInitializer().create();
        fileListener = new DefaultScenarioFileListener();
        scenarioExecutor = new DefaultScenarioExecutor();

        executionService = new DefaultFileExecutionService(
                driver, fileListener, scenarioExecutor
        );
    }

    @Test
    public void testExecute() throws IOException, InterruptedException {
        executionService.execute(testFile);
    }
}