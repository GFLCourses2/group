package executor.service.factory.webdriver;

import executor.service.model.ProxyCredentials;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class WebDriverFactoryProxy implements WebDriverFactory {
    private final WebDriverInitializer initializer;

    public WebDriverFactoryProxy(WebDriverInitializer initializer) {
        this.initializer = initializer;
    }
    @Override
    public WebDriver create() {
        return new WebDriverProxy(initializer);
    }
}
