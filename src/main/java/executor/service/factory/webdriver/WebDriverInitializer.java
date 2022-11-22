package executor.service.factory.webdriver;

import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.model.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverInitializer implements WebDriverFactory {


    private final ProxyConfigHolder proxyConfigHolder;
    private final WebDriverConfig webDriverConfig;


    public WebDriverInitializer(ProxyConfigHolder proxyConfigHolder, WebDriverConfig webDriverConfig) {
        this.proxyConfigHolder = proxyConfigHolder;
        this.webDriverConfig = webDriverConfig;
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

}
