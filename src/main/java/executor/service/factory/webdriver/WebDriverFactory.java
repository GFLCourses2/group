package executor.service.factory.webdriver;

import executor.service.model.ProxyCredentials;
import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver create();
}
