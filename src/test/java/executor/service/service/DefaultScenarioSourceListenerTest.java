package executor.service.service;

import executor.service.factory.WebDriverInitializer;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultScenarioSourceListenerTest {

    private static final File testFile = new File("testScenarios.json");

    private ScenarioSourceListener sourceListener;

    @Before
    public void setUp() {
        WebDriver driver = new WebDriverInitializer().create();
        ScenarioExecutor executor = new DefaultScenarioExecutor();

        sourceListener = new DefaultScenarioSourceListener(driver, testFile, executor);
    }

    @Test
    public void execute() throws IOException, InterruptedException {
        sourceListener.execute();
    }
}