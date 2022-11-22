package executor.service.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class WebDriverProxy implements WebDriver {
    private final WebDriverFactory factory;
    private WebDriver webDriver;

    public WebDriverProxy(WebDriverFactory factory) {
        this.factory = factory;
    }

    private void init() {
        if (webDriver == null)
            webDriver = factory.create();
    }

    @Override
    public void get(String url) {
        init();
        webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        init();
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        init();
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        init();
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        init();
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        init();
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        init();
        webDriver.close();
    }

    @Override
    public void quit() {
        init();
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        init();
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        init();
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        init();
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        init();
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        init();
        return webDriver.manage();
    }
}
