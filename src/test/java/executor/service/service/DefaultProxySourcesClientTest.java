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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DefaultProxySourcesClientTest {
    private ProxySourcesClient proxySourcesClient;
    private List<ProxyConfigHolder> expectedResults;

    @Before
    public void setUp() {
        proxySourcesClient = new DefaultProxySourcesClient(new ObjectMapper(),new ConfigHolder());
        expectedResults = new ArrayList<>();
        expectedResults.add(new ProxyConfigHolder(new ProxyNetworkConfig("host1", 8080),new ProxyCredentials("user11", "pass1")));
        expectedResults.add(new ProxyConfigHolder(new ProxyNetworkConfig("host2", 8088),new ProxyCredentials("user2", "pass2")));
        expectedResults.add(new ProxyConfigHolder(new ProxyNetworkConfig("host3", 8089),new ProxyCredentials("user3", "pass13")));

        expectedResults.add(new ProxyConfigHolder(new ProxyNetworkConfig("host1", 8080),new ProxyCredentials("user11", "pass1")));
        expectedResults.add(new ProxyConfigHolder(new ProxyNetworkConfig("host2", 8088),new ProxyCredentials("user2", "pass2")));
    }

    @Test
    public void testGetProxy() throws IOException {
        for (ProxyConfigHolder expectedResult : expectedResults) {
            assertEquals(expectedResult,proxySourcesClient.getProxy());
        }
    }
}