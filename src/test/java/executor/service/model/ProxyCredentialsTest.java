package executor.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProxyCredentialsTest {

    ProxyCredentials proxyCredentials = new ProxyCredentials("k2777", "1234");
    ProxyCredentials proxyCredentials1 = new ProxyCredentials("k2777", "1234");

    @Test
    public void testEqualsAndHashCode_DTOEquals() {
        assertEquals(proxyCredentials, proxyCredentials1);
        assertEquals(proxyCredentials.hashCode(), proxyCredentials1.hashCode());
    }

    @Test
    public void testEqualsAndHashCode_DTOSame() {
        ProxyCredentials proxyCredentials = new ProxyCredentials("k2777", "1234");
        assertEquals(proxyCredentials, proxyCredentials);
        assertEquals(proxyCredentials.hashCode(), proxyCredentials.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "ProxyCredentials(username=k2777, password=1234)";
        ProxyCredentials proxyCredentials = new ProxyCredentials("k2777", "1234");
        assertEquals(expected, proxyCredentials.toString());
    }
}
