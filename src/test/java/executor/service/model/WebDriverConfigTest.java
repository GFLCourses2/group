package executor.service.model

import static org.junit.Assert.*;

import org.junit.*;

public class WebDriverConfigTest {

    @Test
    public void testEqualsAndHashCode_DTOEquals {
        WebDriverConfig dto1 = new WebDriverConfig();
        WebDriverConfig dto2 = new WebDriverConfig();
        
        dto1.setWebDriverExecutable("driver");
        dto1.setUserAgent("agent");
        dto1.setPageLoadTimeout(1L);
        dto1.setImplicitlyWait(2L);

        dto2.setWebDriverExecutable("driver");
        dto2.setUserAgent("agent");
        dto2.setPageLoadTimeout(1L);
        dto2.setImplicitlyWait(2L);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEqualsAndHashCode_DTOSame {
        WebDriverConfig dto1 = new WebDriverConfig();
        dto1.setWebDriverExecutable("driver");
        dto1.setUserAgent("agent");
        dto1.setPageLoadTimeout(1L);
        dto1.setImplicitlyWait(2L);

        WebDriverConfig dto2 = dto1;

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "{" +
                            "\"webDriverExecutable\":\"driver\"" + 
                            ",\"userAgent\":\"agent\"" + 
                            ",\"pageLoadTimeout\":1" + 
                            ",\"implicitlyWait\":2" + 
                            "}";
        WebDriverConfig dto = new WebDriverConfig();
        dto.setWebDriverExecutable("driver");
        dto.setUserAgent("agent");
        dto.setPageLoadTimeout(1L);
        dto.setImplicitlyWait(2L);

        assertEquals(expected, dto.toString());
    }
}
