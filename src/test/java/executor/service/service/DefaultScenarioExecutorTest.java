package executor.service.service;

import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import executor.service.model.Scenario;
import executor.service.model.Step;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DefaultScenarioExecutorTest {
    private WebDriverFactory webDriverFactory;
    private ScenarioExecutor scenarioExecutor;
    private Scenario scenario;

    @Before
    public void setUp() {
        webDriverFactory = new WebDriverInitializer();
        scenarioExecutor = new DefaultScenarioExecutor();
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("clickcss",
                "#h\\.e02b498c978340a_122 > div > div > p:nth-child(2) > span:nth-child(2) > a"));
        steps.add(new Step("sleep", "5"));
        steps.add(new Step("clickXpath", "//*[@id=\"h.e02b498c978340a_288\"]/div/div/p[3]/span/a"));
        scenario = new Scenario("test1", "https://chromedriver.chromium.org/getting-started", steps);
    }

    @Test
    public void execute() throws InterruptedException {
        WebDriver webDriver = webDriverFactory.create();
        scenarioExecutor.execute(scenario, webDriver);
    }
}