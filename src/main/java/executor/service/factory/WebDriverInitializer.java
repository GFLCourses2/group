package executor.service.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.model.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class WebDriverInitializer implements WebDriverFactory {

    static {
        String os = System.getProperty("os.name");
        String driverProperty = "src/main/java/resources/chromedriver.exe";
        if (os.contains("nux")) {
            driverProperty = "src/main/java/resources/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", driverProperty);
    }

    private final File proxyConfigHolderPath = Paths.get("src/main/java/resources/ProxyConfigHolder.json").toFile();
    private final ProxyConfigHolder proxyConfigHolder;
    private final WebDriverConfig webDriverConfig;

    private final ObjectMapper objectMapper;

    public WebDriverInitializer(ProxyConfigHolder proxyConfigHolder, WebDriverConfig webDriverConfig, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.proxyConfigHolder = proxyConfigHolder;
        this.webDriverConfig = webDriverConfig;
    }

    public WebDriverInitializer() {
        objectMapper = new ObjectMapper();
        proxyConfigHolder = getProxyConfigHolder();
        webDriverConfig = getWebDriverConfig();
    }

    @Override
    public WebDriver create() {
        ChromeOptions chromeOptions = new ChromeOptions();
        setOptions(chromeOptions, proxyConfigHolder, webDriverConfig);

        return new ChromeDriver(chromeOptions);
    }

    private void setOptions(ChromeOptions chromeOptions,
                            ProxyConfigHolder proxyConfigHolder,
                            WebDriverConfig webDriverConfig) {
        setWebDriverConfig(chromeOptions, webDriverConfig);

        //setProxy(chromeOptions, proxyConfigHolder);
    }

    private void setWebDriverConfig(ChromeOptions chromeOptions, WebDriverConfig webDriverConfig) {
        chromeOptions.setPageLoadTimeout(Duration.ofMillis(webDriverConfig.getPageLoadTimeout()));
        chromeOptions.addArguments("user-agent=" + webDriverConfig.getUserAgent());
        chromeOptions.setImplicitWaitTimeout(Duration.ofMillis(webDriverConfig.getImplicitlyWait()));
    }

    private void setProxy(ChromeOptions chromeOptions, ProxyConfigHolder proxyConfigHolder) {
        ProxyCredentials credentials = proxyConfigHolder.getProxyCredentials();
        ProxyNetworkConfig networkConfig = proxyConfigHolder.getProxyNetworkConfig();
        String proxyUrl =
                credentials.getUsername() + ":" + credentials.getPassword() +
                        "@" +
                        networkConfig.getHostName() + ":" + networkConfig.getPort();

        chromeOptions.addArguments("--proxy-server=http://" + proxyUrl);
    }

    private WebDriverConfig getWebDriverConfig() {
        WebDriverConfig result = new WebDriverConfig();
        Properties properties = readProperties();
        result.setWebDriverExecutable(
                properties.getProperty(
                        "executorservice.common.webDriverExecutable",
                        "src/main/java/resources/chromedriver.exe"));

        result.setUserAgent(properties.getProperty("executorservice.common.userAgent", "browser"));
        result.setImplicitlyWait(Long.valueOf(properties.getProperty("executorservice.common.implicitlyWait", "3")));
        result.setPageLoadTimeout(Long.valueOf(properties.getProperty("executorservice.common.pageLoadTimeout", "30000")));
        return result;
    }

    private Properties readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/java/resources/WebDriverConfig.properties")));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ProxyConfigHolder getProxyConfigHolder() {
        try {
            return objectMapper.readValue(proxyConfigHolderPath, ProxyConfigHolder.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
