package executor.service.service;

import executor.service.service.execution.stepexecution.ClickXpath;
import executor.service.service.execution.stepexecution.StepExecution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClickXpathTest {

    private final WebDriverForTests webDriverForTests = new WebDriverForTests();
    private StepExecution stepExecution;

    @Before
    public void setUp() {
        stepExecution = new ClickXpath();
    }

    @Test
    public void testGetStepAction() {
        assertEquals("clickXpath", stepExecution.getStepAction());
    }

//    @Test
//    public void testStep() throws InterruptedException {
//        WebDriver webDriver = webDriverForTests.getWebDriver();
//        Step step = new Step("clickXpath", "/html/body/div/div[1]/div/main/nav/ol/li[1]/a");
//
//        webDriver.get("https://www.selenium.dev/documentation/webdriver/");
//        TimeUnit.SECONDS.sleep(4L);
//        stepExecution.step(webDriver, step);
//        TimeUnit.SECONDS.sleep(4L);
//
//        webDriver.quit();
//    }
}