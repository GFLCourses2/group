package executor.service.service.execution.proxy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.config.ConfigHolder;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultProxySourcesClient implements ProxySourcesClient {
    private static List<ProxyConfigHolder> result;
    private int givenProxyConfigHolder;
    private final ObjectMapper objectMapper;
    private final File credentials;
    private final File networkConfig;

    public DefaultProxySourcesClient(ObjectMapper objectMapper, ConfigHolder configHolder) {
        this.objectMapper = objectMapper;
        this.credentials = configHolder.getProxyCredentialsFile();
        this.networkConfig = configHolder.getProxyNetworkFile();
    }

    @Override
    public ProxyConfigHolder getProxy() throws IOException {
        if (result == null){
            result = new ArrayList<>();
            givenProxyConfigHolder = 0;
            readProxyCredentialsAndNetworkConfigs();
        }

        if (givenProxyConfigHolder == result.size()) {
            givenProxyConfigHolder = 0;
        }

        ProxyConfigHolder tempProxyConfigHolder = result.get(givenProxyConfigHolder);
        givenProxyConfigHolder++;

        return tempProxyConfigHolder;
    }

    private void readProxyCredentialsAndNetworkConfigs() throws IOException {
        List<ProxyCredentials> proxyCredentials = getCredentials();
        List<ProxyNetworkConfig> proxyNetworkConfigs = getNetworkConfigs();
        int minLength = Math.min(proxyCredentials.size(), proxyNetworkConfigs.size());

        for (int i = 0; i < minLength; i++) {
            ProxyConfigHolder proxyConfigHolder = new ProxyConfigHolder(proxyNetworkConfigs.get(i),proxyCredentials.get(i));
            result.add(proxyConfigHolder);
        }
    }

    private List<ProxyCredentials> getCredentials() throws IOException {
        TypeReference<List<ProxyCredentials>> typeReference = new TypeReference<List<ProxyCredentials>>() {};
        return objectMapper.readValue(credentials,typeReference);
    }

    private List<ProxyNetworkConfig> getNetworkConfigs() throws IOException {
        TypeReference<List<ProxyNetworkConfig>> typeReference = new TypeReference<List<ProxyNetworkConfig>>() {};
        return objectMapper.readValue(networkConfig,typeReference);
    }
}
