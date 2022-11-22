package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.ClickXpath;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ClickXpathTest {
    private ServiceFactory factory;
    private StepExecution stepExecution;

    @Before
    public void setUp() {
        factory = new DefaultServiceFactory();
        stepExecution = factory.create(ClickXpath.class);
    }

    @Test
    public void testGetStepAction() {
        assertEquals("clickXpath", stepExecution.getStepAction());
    }

    @Test
    public void testStep() throws InterruptedException {
        WebDriver webDriver = factory.create(WebDriver.class);
        Step step = new Step("clickXpath", "/html/body/div/div[1]/div/main/nav/ol/li[1]/a");
        webDriver.get("https://www.selenium.dev/documentation/webdriver/");
        TimeUnit.SECONDS.sleep(4L);
        stepExecution.step(webDriver, step);
        TimeUnit.SECONDS.sleep(4L);
        webDriver.quit();
    }
}