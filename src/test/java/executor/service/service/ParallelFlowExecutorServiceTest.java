package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.service.execution.executionservice.ParallelFlowExecutorService;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

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
    public void testMethodExecuteParallelExecutorService() {
        try {

        } finally {
            webDriver.quit();
        }
    }
}