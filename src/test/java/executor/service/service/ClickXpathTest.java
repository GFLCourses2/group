package executor.service.service;

import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import executor.service.model.Step;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ClickXpathTest extends TestCase {
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
        Step step = new Step("clickXpath", "//*[@id=\"gb\"]/div/div[1]/div/div[1]/a");
        webDriver.get("https://www.google.com.ua/");
        TimeUnit.SECONDS.sleep(4L);
        stepExecution.step(webDriver, step);
        TimeUnit.SECONDS.sleep(4L);
        webDriver.quit();
    }
}