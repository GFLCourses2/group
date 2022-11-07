package executor.service.model;

import org.junit.*;
import static org.junit.Assert.*;

public class ProxyConfigHolderTest {
    ProxyNetworkConfig proxyNetworkConfig = new ProxyNetworkConfig("TestHost",1);
    ProxyCredentials proxyCredentials = new ProxyCredentials("TestName","TestPassword");

    @Test
    public void testEqualsAndHash(){
        ProxyConfigHolder proxyConfHolder1 = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);
        ProxyConfigHolder proxyConfHolder2 = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);

        assertEquals(proxyConfHolder1,proxyConfHolder2);
        assertEquals(proxyConfHolder1.hashCode(),proxyConfHolder2.hashCode());
    }

    @Test
    public void testSameObjs(){
        ProxyConfigHolder proxyConfHolder1 = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);

        ProxyConfigHolder proxyConfHolder2 = proxyConfHolder1;

        assertEquals(proxyConfHolder1,proxyConfHolder2);
        assertEquals(proxyConfHolder1.hashCode(),proxyConfHolder2.hashCode());
    }

    @Test
    public void testToString(){
        String expected = '{' +
                "\"proxyNetworkConfig\"" + ':' + '\"' + "{" + "\"hostname\":\"" + "TestHost" + "\"," + "\"port\":" + 1 + "}" + '\"' + ',' +
                "\"proxyCredentials\"" + ':' + '\"' + "{" + "\"username=\":" + '\"' + "TestName" + '\"' +
                ",\"password=\":" + '\"' + "TestPassword" + '\"' + '}' + '\"' + '}';
        ProxyConfigHolder proxyConfHolder1 = new ProxyConfigHolder(proxyNetworkConfig,proxyCredentials);
        assertEquals(expected,proxyConfHolder1.toString());
    }
}