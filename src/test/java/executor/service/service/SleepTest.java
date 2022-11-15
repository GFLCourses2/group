package executor.service.service;

import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import executor.service.model.Step;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

public class SleepTest {

    private WebDriverFactory webDriverFactory;
    private StepExecution stepSleep;

    @Before
    public void setUp() {
        webDriverFactory = new WebDriverInitializer();
        stepSleep = new Sleep();
    }

    @Test
    public void testGetStepAction() {
        Assert.assertEquals("Sleep", stepSleep.getStepAction());
    }

    @Test
    public void testStep() {
        Step step = new Step();
        step.setAction("sleep");
        step.setValue("3");


        WebDriver webDriver = webDriverFactory.create();
        webDriver.get("https://google.com/");

        stepSleep.step(webDriver, step);

        webDriver.quit();
    }
}