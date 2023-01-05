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
            String prodPath = "classes/chromedriver.exe";
            System.setProperty(
                    "webdriver.chrome.driver", prodPath);
        }
    }

    @Override
    public WebDriver create() {
        ChromeOptions chromeOptions = new ChromeOptions();
        setOptions(chromeOptions, proxyHandler.getProxy(), webDriverConfig);
        return new ChromeDriver(chromeOptions);
    }

    private void setOptions(ChromeOptions chromeOptions,
                            Optional<ProxyConfigHolder> proxyConfigHolderOptional,
                            WebDriverConfigProperties webDriverConfig) {

//        chromeOptions.addArguments("--no-sandbox");
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
