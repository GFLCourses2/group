package executor.service.service.execution.proxy;

import com.fasterxml.jackson.databind.DatabindException;
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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(credentials, ProxyCredentials[].class);
        } catch (DatabindException de) {
            System.out.println(de.getMessage());
            List<ProxyCredentials> cr = objectMapper.readValue(credentials, objectMapper.getTypeFactory().constructCollectionType(List.class, ProxyCredentials.class));
            return cr.toArray(new ProxyCredentials[0]);
        }
    }

    private ProxyNetworkConfig[] getNetworkConfigs() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(networkConfig, ProxyNetworkConfig[].class);
        } catch (DatabindException de) {
            List<ProxyNetworkConfig> ch = mapper.readValue(networkConfig, mapper.getTypeFactory().constructCollectionType(List.class, ProxyNetworkConfig.class));
            return ch.toArray(new ProxyNetworkConfig[0]);
        }
    }
}
