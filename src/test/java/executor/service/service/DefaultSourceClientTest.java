package executor.service.service;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DefaultSourceClientTest {
    private static final File testFileCredentials = new File("ProxyCredentials.json");
    private static final File testFileNetwork = new File("ProxyNetwork.json");
    private ProxySourcesClient proxySourcesClient;
    @Before
    public void setUp(){
        proxySourcesClient = new DefaultProxySourcesClient(testFileNetwork, testFileCredentials);
    }
    @Test
    public void testGetProxy() throws IOException {
        proxySourcesClient.getProxy();
    }
}
