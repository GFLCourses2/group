package executor.service.factory.webdriver;

import executor.service.config.ConfigHolder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
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
