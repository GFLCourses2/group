package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.ClickCss;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ClickCssTest {

    private StepExecution stepExecution;

    private ServiceFactory factory;

    @Before
    public void setUp() {
        factory = new DefaultServiceFactory();
        stepExecution = factory.create(ClickCss.class);
    }

    @Test
    public void getStepAction() {
        assertEquals("clickcss", stepExecution.getStepAction());

    }

    @Test
    public void step() throws InterruptedException {
        WebDriver webDriver = factory.create(WebDriver.class);
        Step step = new Step();
        step.setAction("сlickсss");
        step.setValue("body > div > div.td-main > div > main > nav > ol > li:nth-child(1) > a");

        webDriver.get("https://www.selenium.dev/documentation/webdriver/");
        TimeUnit.SECONDS.sleep(3L);
        stepExecution.step(webDriver, step);
        TimeUnit.SECONDS.sleep(3L);

        webDriver.quit();
    }
}