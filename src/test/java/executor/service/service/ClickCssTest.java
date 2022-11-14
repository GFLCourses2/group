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
        step.setAction("ClickCss");
        step.setValue("body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.FPdoLc.lJ9FBc > center > input.RNmpXc");

        webDriver.get("https://www.google.com");
        TimeUnit.SECONDS.sleep(3L);
        stepExecution.step(webDriver, step);
        TimeUnit.SECONDS.sleep(3L);

        webDriver.quit();
    }
}