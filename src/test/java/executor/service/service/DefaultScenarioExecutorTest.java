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

import static org.junit.Assert.*;

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
                "body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.FPdoLc.lJ9FBc > center > input.RNmpXc"));
        steps.add(new Step("sleep","5"));
        steps.add(new Step("clickXpath","//*[@id=\"about-link\"]"));
        scenario = new Scenario("test1","https://www.google.com",steps);
    }

    @Test
    public void execute() throws InterruptedException {
        WebDriver webDriver = webDriverFactory.create();
        scenarioExecutor.execute(scenario,webDriver);
    }
}