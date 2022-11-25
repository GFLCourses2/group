package executor.service.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.execution.executionservice.DefaultScenarioExecutor;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DefaultScenarioExecutorTest {
    private WebDriverFactory webDriverFactory;
    private DefaultScenarioExecutor scenarioExecutor;
    private Scenario scenario;

    @Before
    public void setUp() {
        ServiceFactory factory = new DefaultServiceFactory();
        webDriverFactory = factory.create(WebDriverFactory.class);
        scenarioExecutor = factory.create(DefaultScenarioExecutor.class);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("clickcss",
                "#h\\.e02b498c978340a_122 > div > div > p:nth-child(2) > span:nth-child(2) > a"));
        steps.add(new Step("sleep", "5"));
        steps.add(new Step("clickXpath", "//*[@id=\"h.e02b498c978340a_288\"]/div/div/p[3]/span/a"));
        steps.add(new Step("sleep", "3"));
        scenario = new Scenario("test1", "https://chromedriver.chromium.org/getting-started", steps);
    }

    @Test
    public void execute() {
        WebDriver webDriver = webDriverFactory.create();
        Runnable runnable = mock(Runnable.class);
        scenarioExecutor.executeWithCallback(scenario, webDriver, runnable);
        verify(runnable, times(4)).run();
        webDriver.quit();
    }
}