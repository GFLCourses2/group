package executor.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.factory.webdriver.WebDriverProxy;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.WebDriverConfig;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConfig {
    static {
        String os = System.getProperty("os.name");
        String driverProperty = "src/main/java/resources/chromedriver.exe";
        if (os.contains("nux")) {
            driverProperty = "src/main/java/resources/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", driverProperty);
    }

    private final ConfigHolder configHolder;

    private final WebDriverFactory webDriverFactory;

    private final Map<Class<?>, Object> implementations = new HashMap<>();


    public ApplicationConfig(ConfigHolder configHolder, WebDriverFactory webDriverFactory) {
        this.configHolder = configHolder;
        this.webDriverFactory = webDriverFactory;
        initImplementations();
    }

    private void initImplementations() {
        implementations.put(ProxyConfigHolder.class, configHolder.getProxyConfigHolder());
        implementations.put(WebDriverConfig.class, configHolder.getWebDriverConfig());
        implementations.put(ObjectMapper.class, new ObjectMapper());
        implementations.put(WebDriver.class, new WebDriverProxy(webDriverFactory));
    }

    public <T> T getImplementation(Class<T> clazz) {
        return (T) implementations.get(clazz);
    }


}
