package executor.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProxyNetworkConfigTest {
    private static ProxyNetworkConfig proxyNetworkConfig1;
    private static ProxyNetworkConfig proxyNetworkConfig2;

    public static void createObjects() {
        proxyNetworkConfig1 = new ProxyNetworkConfig();
        proxyNetworkConfig2 = new ProxyNetworkConfig();

        proxyNetworkConfig1.setHostName("geekforless.com.ua");
        proxyNetworkConfig1.setPort(8080);

        proxyNetworkConfig2.setHostName("geekforless.com.ua");
        proxyNetworkConfig2.setPort(8080);
    }

    @Test()
    public void testHashCodeWhenObjectsEqual() {
        createObjects();
        assertEquals(proxyNetworkConfig1.hashCode(), proxyNetworkConfig2.hashCode());
    }

    @Test()
    public void testEqualsWhenObjectsEqual() {
        createObjects();
        assertEquals(proxyNetworkConfig1, proxyNetworkConfig2);
    }

    @Test()
    public void testHashCodeWhenObjectsNotEqual() {
        createObjects();
        proxyNetworkConfig1.setPort(8081);
        assertNotEquals(proxyNetworkConfig1.hashCode(), proxyNetworkConfig2.hashCode());
    }

    @Test()
    public void testEqualsWhenObjectsNotEqual() {
        createObjects();
        proxyNetworkConfig1.setHostName("geekforless.com.eu");
        assertNotEquals(proxyNetworkConfig1, proxyNetworkConfig2);
    }

    @Test
    public void testToString() {
        createObjects();
        String expected = "{" + "\"hostname\":\""
                + "geekforless.com.ua"
                + "\"," + "\"port\":"
                + 8080 + "}";
        assertEquals(expected, proxyNetworkConfig1.toString());
    }
}