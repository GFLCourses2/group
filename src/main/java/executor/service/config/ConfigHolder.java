package executor.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigHolder {
    private final Path proxyConfigHolderPath = Paths.get("src/main/java/resources/proxyConfigHolder.json");

    private final File proxyCredentialsFile = new File ("ProxyCredentials.json");
    private final File proxyNetworkFile = new File ("ProxyNetwork.json");

    private final File scenarioFile = new File("testScenarios.json");

    private final ProxyConfigHolder proxyConfigHolder;

    public ConfigHolder() {
        this.proxyConfigHolder = readProxyConfigHolder();
    }

    public File getScenarioFile() {
        return scenarioFile;
    }

    public ProxyConfigHolder getProxyConfigHolder() {
        return proxyConfigHolder;
    }

    public File getProxyCredentialsFile() {
        return proxyCredentialsFile;
    }

    public File getProxyNetworkFile() {
        return proxyNetworkFile;
    }

    private ProxyConfigHolder readProxyConfigHolder() {
        try {
            return new ObjectMapper().readValue(proxyConfigHolderPath.toFile(), ProxyConfigHolder.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
