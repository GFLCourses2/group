package executor.service.service;

import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import executor.service.model.Step;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ClickXpathTest {
    private WebDriverFactory webDriverFactory;
    private StepExecution stepExecution;

    @Before
    public void setUp() {
        webDriverFactory = new WebDriverInitializer();
        stepExecution = new ClickXpath();
    }

    @Test
    public void testGetStepAction() {
        assertEquals("clickXpath", stepExecution.getStepAction());
    }

    @Test
    public void testStep() throws InterruptedException {
        WebDriver webDriver = webDriverFactory.create();
        Step step = new Step("clickXpath", "/html/body/div/div[1]/div/main/nav/ol/li[1]/a");
        webDriver.get("https://www.selenium.dev/documentation/webdriver/");
        TimeUnit.SECONDS.sleep(4L);
        stepExecution.step(webDriver, step);
        TimeUnit.SECONDS.sleep(4L);
        webDriver.quit();
    }
}