package executor.service.factory.webdriver;

import executor.service.config.WebDriverConfigProperties;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.service.execution.proxy.ProxySourcesClient;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Qualifier("webDriverFactoryCommon")
@AllArgsConstructor
public class WebDriverInitializer implements WebDriverFactory {

    private final ProxySourcesClient proxySourcesClient;
    private final WebDriverConfigProperties webDriverConfig;

    @PostConstruct
    private void initWebDriverExecutable() {
        String pathToWebDriverExecutable = "src/main/";
        System.setProperty(
                "webdriver.chrome.driver", pathToWebDriverExecutable + webDriverConfig.getWebDriverExecutable());
    }

    @Override
    public WebDriver create() {
        ChromeOptions chromeOptions = new ChromeOptions();
        setOptions(chromeOptions, proxySourcesClient.getProxy(), webDriverConfig);
        return new ChromeDriver(chromeOptions);
    }

    private void setOptions(ChromeOptions chromeOptions,
                            ProxyConfigHolder proxyConfigHolder,
                            WebDriverConfigProperties webDriverConfig) {
        setWebDriverConfig(chromeOptions, webDriverConfig);
        setProxy(chromeOptions, proxyConfigHolder);
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
