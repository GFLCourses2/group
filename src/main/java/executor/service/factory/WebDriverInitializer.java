package executor.service.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverInitializer implements WebDriverFactory {

    static {
        String os = System.getProperty("os.name");
        String driverProperty= "src/main/java/resources/chromedriver.exe";
        if (os.contains("nux")) {
            driverProperty = "src/main/java/resources/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", driverProperty);
    }

    @Override
    public WebDriver create() {

        return new ChromeDriver();
    }
}
