package executor.service.service;

import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import executor.service.model.Step;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ClickCssTest {

    private StepExecution stepExecution;

    private WebDriverFactory webDriverFactory;

    @Before
    public void setUp() {
        stepExecution = new ClickCss();
        webDriverFactory = new WebDriverInitializer();
    }

    @Test
    public void getStepAction() {
        assertEquals("clickcss", stepExecution.getStepAction());
    }

    @Test
    public void step() throws InterruptedException {
        WebDriver webDriver = webDriverFactory.create();
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