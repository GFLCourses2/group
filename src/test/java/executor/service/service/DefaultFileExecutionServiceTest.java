package executor.service.service;

import executor.service.factory.DefaultServiceFactory;
import executor.service.factory.ServiceFactory;
import executor.service.factory.WebDriverInitializer;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultFileExecutionServiceTest {

    private static final File testFile = new File("testScenarios.json");

    private FileExecutionService executionService;

    @Before
    public void init() {
        ServiceFactory factory = new DefaultServiceFactory();

        executionService = factory.create(FileExecutionService.class);
    }

    @Test
    public void testExecute() throws IOException, InterruptedException {
        executionService.execute(testFile);
    }
}