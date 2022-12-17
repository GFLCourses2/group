package executor.service.config;

import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.factory.webdriver.WebDriverFactoryProxy;
import executor.service.factory.webdriver.WebDriverInitializer;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactoryConfig {
    private final WebDriverFactory webDriverFactory;

    public WebDriverFactoryConfig(@Qualifier("webDriverFactoryCommon") WebDriverInitializer webDriverInitializer) {
        this.webDriverFactory = new WebDriverFactoryProxy(webDriverInitializer);
    }

    @Bean
    public WebDriver webDriver() {
        return webDriverFactory.create();
    }
}
