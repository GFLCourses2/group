package executor.service.factory;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverInitializerTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverFactory factory = new WebDriverInitializer();
        driver = factory.create();
    }

    @Test
    public void initWebDriverTest() throws InterruptedException {

        driver.get("http://info.cern.ch");
        TimeUnit.SECONDS.sleep(5L);
        driver.quit();
    }

}