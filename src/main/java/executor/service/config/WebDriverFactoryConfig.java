package executor.service.config;

import executor.service.factory.webdriver.WebDriverFactory;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WebDriverFactoryConfig {
    private final WebDriverFactory webDriverFactory;

    @Bean
    @Lazy
    public WebDriver webDriver() {
        return webDriverFactory.create();
    }
}
