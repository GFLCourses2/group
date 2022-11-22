package executor.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.WebDriverConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigHolder {
    private final Path proxyConfigHolderPath = Paths.get("src/main/java/resources/ProxyConfigHolder.json");

    private final Path webDriverConfigPath = Paths.get("src/main/java/resources/WebDriverConfig.properties");

    private final File scenarioFile = new File("testScenarios.json");

    private final ProxyConfigHolder proxyConfigHolder;

    private final WebDriverConfig webDriverConfig;

    public ConfigHolder() {
        this.proxyConfigHolder = readProxyConfigHolder();
        this.webDriverConfig = readWebDriverConfig();
    }

    public File getScenarioFile() {
        return scenarioFile;
    }

    public ProxyConfigHolder getProxyConfigHolder() {
        return proxyConfigHolder;
    }

    public WebDriverConfig getWebDriverConfig() {
        return webDriverConfig;
    }

    private WebDriverConfig readWebDriverConfig() {
        WebDriverConfig result = new WebDriverConfig();
        Properties properties = readWebDriverProperties();
        result.setWebDriverExecutable(
                properties.getProperty(
                        "executorservice.common.webDriverExecutable",
                        "src/main/java/resources/chromedriver.exe"));

        result.setUserAgent(properties.getProperty("executorservice.common.userAgent", "browser"));
        result.setImplicitlyWait(Long.valueOf(properties.getProperty("executorservice.common.implicitlyWait", "3")));
        result.setPageLoadTimeout(Long.valueOf(properties.getProperty("executorservice.common.pageLoadTimeout", "30000")));
        return result;
    }

    private ProxyConfigHolder readProxyConfigHolder() {
        try {
            return new ObjectMapper().readValue(proxyConfigHolderPath.toFile(), ProxyConfigHolder.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties readWebDriverProperties() {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(webDriverConfigPath));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
