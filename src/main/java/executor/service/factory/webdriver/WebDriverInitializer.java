package executor.service.factory.webdriver;

import executor.service.config.properties.WebDriverConfigProperties;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyNetworkConfig;
import executor.service.service.holder.proxy.ProxyHandler;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Qualifier("webDriverFactoryCommon")
@AllArgsConstructor
public class WebDriverInitializer implements WebDriverFactory {

    private final ProxyHandler proxyHandler;
    private final WebDriverConfigProperties webDriverConfig;

    @PostConstruct
    private void initWebDriverExecutable() {
        String debugPath = "src/main/" + webDriverConfig.getWebDriverExecutable();
        if (new File(debugPath).exists()) {
            System.setProperty(
                    "webdriver.chrome.driver", debugPath);
        } else {
            String fullProdPath = "/home/ubuntu/chromedriver.exe";
            System.setProperty(
                    "webdriver.chrome.driver", fullProdPath);
        }
    }

    @Override
    public WebDriver create() {
        ChromeOptions chromeOptions = new ChromeOptions();
        setOptions(chromeOptions, proxyHandler.getProxy(), webDriverConfig);
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.of(5, TimeUnit.SECONDS.toChronoUnit()));
        return driver;
    }

    private void setOptions(ChromeOptions chromeOptions,
                            Optional<ProxyConfigHolder> proxyConfigHolderOptional,
                            WebDriverConfigProperties webDriverConfig) {

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        setWebDriverConfig(chromeOptions, webDriverConfig);
        proxyConfigHolderOptional.ifPresent(proxyConfigHolder -> setProxy(chromeOptions, proxyConfigHolder));
    }

    private void setWebDriverConfig(ChromeOptions chromeOptions, WebDriverConfigProperties webDriverConfig) {
        chromeOptions.setPageLoadTimeout(Duration.ofMillis(webDriverConfig.getPageLoadTimeout()));
        chromeOptions.addArguments("user-agent=" + webDriverConfig.getUserAgent());
        chromeOptions.setImplicitWaitTimeout(Duration.ofMillis(webDriverConfig.getImplicitlyWait()));
    }

    private void setProxy(ChromeOptions chromeOptions, ProxyConfigHolder proxyConfigHolder) {
        ProxyNetworkConfig networkConfig = proxyConfigHolder.getProxyNetworkConfig();
        String url = networkConfig.getHostName() + ':' + networkConfig.getPort();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(url);
        proxy.setSslProxy(url);
        chromeOptions.setProxy(proxy);
    }

}
