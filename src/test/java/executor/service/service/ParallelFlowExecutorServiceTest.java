package executor.service.service;

import executor.service.factory.DefaultServiceFactory;
import executor.service.factory.ServiceFactory;
import executor.service.factory.WebDriverFactory;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ParallelFlowExecutorServiceTest {
    private ParallelFlowExecutorService parallelExecutorService;
    private WebDriver webDriver;

    @Before
    public void setUp() {
        ServiceFactory factory = new DefaultServiceFactory();
        WebDriverFactory webDriverFactory = factory.create(WebDriverFactory.class);
        parallelExecutorService = factory.create(ParallelFlowExecutorService.class);
        webDriver = webDriverFactory.create();
    }

    @Test
    public void testMethodExecuteParallelExecutorService() throws IOException {
        try {
            parallelExecutorService.execute();
        } finally {
            webDriver.quit();
        }
    }
}