package executor.service.service.execution.proxy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DefaultProxySourcesClient implements ProxySourcesClient {
    private final File credentials;
    private final File networkConfig;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public DefaultProxySourcesClient(File credentials, File networkConfig) {
        this.credentials = credentials;
        this.networkConfig = networkConfig;
    }

    @Override
    public ProxyConfigHolder getProxy() throws IOException {
        List<ProxyConfigHolder> result = new ArrayList<>();
        ProxyCredentials[] proxyCredentials = getCredentials();
        ProxyNetworkConfig[] proxyNetworkConfigs = getNetworkConfigs();
        int minLength = Math.min(proxyCredentials.length, proxyNetworkConfigs.length);

        for (int i = 0; i < minLength; i++) {
            ProxyConfigHolder proxyConfigHolder = new ProxyConfigHolder(proxyNetworkConfigs[i], proxyCredentials[i]);
            result.add(proxyConfigHolder);
        }

        return result.get(0);
    }

    private ProxyCredentials[] getCredentials() throws IOException {
        TypeReference<ProxyCredentials[]> typeReference = new TypeReference<ProxyCredentials[]>() {};
        return objectMapper.readValue(credentials,typeReference);
    }

    private ProxyNetworkConfig[] getNetworkConfigs() throws IOException {
        TypeReference<ProxyNetworkConfig[]> typeReference = new TypeReference<ProxyNetworkConfig[]>() {};
        return objectMapper.readValue(networkConfig,typeReference);
    }
}
