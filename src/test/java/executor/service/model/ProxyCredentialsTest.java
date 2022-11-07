package executor.service.model;

import org.junit.*;
import static org.junit.Assert.*;

public class ProxyCredentialsTest {
    @Test
    public void testEqualsAndHashCode_DTOEquals(){
        ProxyCredentials proxyCredentials = new ProxyCredentials("k2777","1234");
        ProxyCredentials proxyCredentials1 = new ProxyCredentials("k2777","1234");
        assertEquals(proxyCredentials,proxyCredentials1);
        assertEquals(proxyCredentials.hashCode(), proxyCredentials1.hashCode());
    }
    @Test
    public void testEqualsAndHashCode_DTOSame(){
        ProxyCredentials proxyCredentials = new ProxyCredentials("k2777","1234");
        ProxyCredentials proxyCredentials1 = proxyCredentials;
        assertEquals(proxyCredentials, proxyCredentials1);
        assertEquals(proxyCredentials.hashCode(), proxyCredentials1.hashCode());

    }
    @Test
    public void testToString() {
        String expected = "{" +
                "\"username=\":\"" + "k2777" + '\'' +
                ",\" password=\":\"" + "1234" +
                '}';
        ProxyCredentials proxyCredentials = new ProxyCredentials("k2777","1234");
        assertEquals(expected, proxyCredentials.toString());
    }
}
