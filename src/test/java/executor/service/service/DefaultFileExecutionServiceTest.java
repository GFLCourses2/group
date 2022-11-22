package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.service.execution.executionservice.FileExecutionService;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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