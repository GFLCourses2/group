package executor.service.service;

import executor.service.model.ProxyConfigHolder;
import executor.service.model.ProxyCredentials;
import executor.service.model.ProxyNetworkConfig;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DefaultProxySourcesClientTest {
    private static final File testFileCredentials = new File("ProxyCredentials.json");
    private static final File testFileNetwork = new File("ProxyNetwork.json");
    private ProxySourcesClient proxySourcesClient;

    @Before
    public void setUp() {
        proxySourcesClient = new DefaultProxySourcesClient(testFileCredentials, testFileNetwork);
    }

    @Test
    public void testGetProxy() throws IOException {
        ProxyConfigHolder proxyConfigHolder = proxySourcesClient.getProxy();
        assertEquals(new ProxyNetworkConfig("host1", 8080), proxyConfigHolder.getProxyNetworkConfig());
        assertEquals(new ProxyCredentials("user11", "pass1"), proxyConfigHolder.getProxyCredentials());
    }
}