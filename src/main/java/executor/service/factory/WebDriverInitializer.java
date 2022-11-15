package executor.service.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverInitializer implements WebDriverFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
    }

    @Override
    public WebDriver create() {

        return new ChromeDriver();
    }
}
