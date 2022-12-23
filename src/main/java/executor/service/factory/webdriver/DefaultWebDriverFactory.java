package executor.service.factory.webdriver;

import executor.service.config.ConfigHolder;
import org.openqa.selenium.WebDriver;

public class DefaultWebDriverFactory implements WebDriverFactory {
    private final WebDriverInitializer initializer;

    public DefaultWebDriverFactory(ConfigHolder configHolder) {
        this.initializer = new WebDriverInitializer(configHolder);
    }


    @Override
    public WebDriver create() {
        return new WebDriverProxy(initializer);
    }
}
