package executor.service.service;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DefaultProxySourcesClient implements ProxySourcesClient {
    private final File credentials ;
    private final File networkConfig;
    public DefaultProxySourcesClient(File credentials, File networkConfig) {
        this.credentials = credentials;
        this.networkConfig = networkConfig;
    }

    @Override
    public ProxyConfigHolder[] getProxy() throws IOException {
        List<ProxyConfigHolder> tmp = new ArrayList<>();
        ProxyCredentials[] proxyCredentials = getCredential();
        ProxyNetworkConfig[] proxyNetworkConfigs = getNetworkConfig();
        int max = Math.max(proxyCredentials.length, proxyNetworkConfigs.length);
        for (int i=0; i < max; i++ ){
            ProxyConfigHolder proxyConfigHolder = new ProxyConfigHolder();
            if(max<proxyCredentials.length) proxyConfigHolder.setProxyCredentials(proxyCredentials[i]);
            if(max<proxyNetworkConfigs.length) proxyConfigHolder.setProxyNetworkConfig(proxyNetworkConfigs[i]);
            tmp.add(proxyConfigHolder);
        }
        return tmp.toArray(new ProxyConfigHolder[0]);
    }

    public ProxyCredentials[] getCredential() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(credentials, ProxyCredentials[].class);
        } catch (DatabindException de) {
            List<ProxyCredentials> cr = mapper.readValue(credentials, mapper.getTypeFactory().constructCollectionType(List.class, ProxyCredentials.class));
            return cr.toArray(new ProxyCredentials[0]);
        }
    }
    public ProxyNetworkConfig[] getNetworkConfig() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(networkConfig, ProxyNetworkConfig[].class);
        } catch (DatabindException de) {
            List<ProxyNetworkConfig> ch = mapper.readValue(networkConfig, mapper.getTypeFactory().constructCollectionType(List.class, ProxyNetworkConfig.class));
            return ch.toArray(new ProxyNetworkConfig[0]);
        }
    }
}
