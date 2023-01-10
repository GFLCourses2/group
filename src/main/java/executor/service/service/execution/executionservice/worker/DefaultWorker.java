package executor.service.service.execution.executionservice.worker;

import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.factory.webdriver.WebDriverProxy;
import executor.service.service.execution.executionservice.ScenarioExecutor;
import executor.service.service.holder.scenario.ScenarioHolder;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.CountDownLatch;

public class DefaultWorker extends Worker {
    private final WebDriver webDriver;

    public DefaultWorker(ScenarioHolder scenarioHolder,
                         ScenarioExecutor scenarioExecutor,
                         CountDownLatch countDownLatch,
                         WebDriverFactory webDriverFactory) {
        super(scenarioHolder, scenarioExecutor, countDownLatch);
        this.webDriver = new WebDriverProxy(webDriverFactory);
    }

    @Override
    protected WebDriver getWebDriver() {
        return webDriver;
    }
}
