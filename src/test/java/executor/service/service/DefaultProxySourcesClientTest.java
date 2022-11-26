package executor.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.config.ConfigHolder;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import executor.service.service.execution.proxy.DefaultProxySourcesClient;
import executor.service.service.execution.proxy.ProxySourcesClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DefaultProxySourcesClientTest {
    private ProxySourcesClient proxySourcesClient;

    @Before
    public void setUp() {
        proxySourcesClient = new DefaultProxySourcesClient(new ObjectMapper(),new ConfigHolder());
    }

    @Test
    public void testGetProxy() throws IOException {
        ProxyConfigHolder proxyConfigHolder1 = proxySourcesClient.getProxy();
        assertEquals(new ProxyNetworkConfig("host1", 8080), proxyConfigHolder1.getProxyNetworkConfig());
        assertEquals(new ProxyCredentials("user11", "pass1"), proxyConfigHolder1.getProxyCredentials());

        ProxyConfigHolder proxyConfigHolder2 = proxySourcesClient.getProxy();
        assertEquals(new ProxyNetworkConfig("host2", 8088), proxyConfigHolder2.getProxyNetworkConfig());
        assertEquals(new ProxyCredentials("user2", "pass2"), proxyConfigHolder2.getProxyCredentials());

        ProxyConfigHolder proxyConfigHolder3 = proxySourcesClient.getProxy();
        assertEquals(new ProxyNetworkConfig("host3", 8089), proxyConfigHolder3.getProxyNetworkConfig());
        assertEquals(new ProxyCredentials("user3", "pass13"), proxyConfigHolder3.getProxyCredentials());
    }
}