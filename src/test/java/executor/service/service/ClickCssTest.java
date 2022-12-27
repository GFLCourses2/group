package executor.service.service;

import executor.service.service.execution.stepexecution.ClickCss;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClickCssTest {

    private final WebDriverForTests webDriverForTests = new WebDriverForTests();
    private StepExecution stepExecution;

    @Before
    public void setUp() {
        stepExecution = new ClickCss();
    }

    @Test
    public void getStepAction() {
        assertEquals("clickcss", stepExecution.getStepAction());
    }

    @Test
    public void step() throws InterruptedException {
//        WebDriver webDriver = webDriverForTests.getWebDriver();
//        Step step = new Step("сlickсss", "body > div > div.td-main > div > main > nav > ol > li:nth-child(1) > a");
//
//        webDriver.get("https://www.selenium.dev/documentation/webdriver/");
//        TimeUnit.SECONDS.sleep(3L);
//        stepExecution.step(webDriver, step);
//        TimeUnit.SECONDS.sleep(3L);
//
//        webDriver.quit();
    }
}