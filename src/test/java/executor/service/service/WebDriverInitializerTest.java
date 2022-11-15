package executor.service.service;


import executor.service.factory.WebDriverFactory;
import executor.service.factory.WebDriverInitializer;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverInitializerTest {

    @Test
    public void initWebDriverTest() throws InterruptedException {
        WebDriverFactory initializer = new WebDriverInitializer();
        WebDriver driver = initializer.create();

        driver.get("http://info.cern.ch");
        TimeUnit.SECONDS.sleep(5L);
        driver.quit();
    }

}