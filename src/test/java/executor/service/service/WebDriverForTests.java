package executor.service.service;

import executor.service.config.WebDriverConfigProperties;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.factory.webdriver.WebDriverInitializer;
import executor.service.service.execution.proxy.DefaultProxySourcesClient;
import org.openqa.selenium.WebDriver;

public class WebDriverForTests {

    public WebDriver getWebDriver() {
        WebDriverConfigProperties webDriverConfigProperties = new WebDriverConfigProperties();
        webDriverConfigProperties.setPageLoadTimeout(3000L);
        webDriverConfigProperties.setWebDriverExecutable("resources/chromedriver.exe");
        webDriverConfigProperties.setUserAgent("desktop");
        webDriverConfigProperties.setImplicitlyWait(1L);

        WebDriverFactory webDriverFactory = new WebDriverInitializer(new DefaultProxySourcesClient(), webDriverConfigProperties);

        return webDriverFactory.create();
    }
}