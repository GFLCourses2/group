package executor.service.service;

import executor.service.model.Step;
import executor.service.service.execution.stepexecution.Sleep;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SleepTest {

    private final WebDriverForTests webDriverForTests = new WebDriverForTests();
    private StepExecution stepSleep;

    @Before
    public void setUp() {
        stepSleep = new Sleep();
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

        WebDriver webDriver = webDriverForTests.getWebDriver();
        webDriver.get("https://google.com/");

        stepSleep.step(webDriver, step);

        webDriver.quit();
    }
}