package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.Sleep;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SleepTest {

    private ServiceFactory factory;
    private StepExecution stepSleep;

    @Before
    public void setUp() {
        factory = new DefaultServiceFactory();
        stepSleep = factory.create(Sleep.class);
    }

    @Test
    public void testGetStepAction() {
        Assert.assertEquals("sleep", stepSleep.getStepAction());
    }

    @Test
    public void testStep() {
        Step step = new Step();
        step.setAction("sleep");
        step.setValue("3");

        WebDriver webDriver = factory.create(WebDriver.class);
        webDriver.get("https://google.com/");

        stepSleep.step(webDriver, step);

        webDriver.quit();
    }
}