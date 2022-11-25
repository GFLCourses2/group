package executor.service.config;

import executor.service.model.ThreadPoolConfig;
import executor.service.model.WebDriverConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class ConfigHolderTest {

    private ConfigHolder configHolder;
    private ThreadPoolConfig threadPoolConfig;
    private WebDriverConfig webDriverConfig;

    @Before
    public void setUp() {
        configHolder = new ConfigHolder();
        threadPoolConfig = configHolder.getThreadPoolConfig();
        webDriverConfig = configHolder.getWebDriverConfig();
    }

    @Test
    public void threadPoolConfigTest() {
        ThreadPoolConfig threadPoolConfig = configHolder.getThreadPoolConfig();
        assertNotEquals(null, threadPoolConfig.getCorePoolSize());
        assertNotEquals(Integer.valueOf(0), threadPoolConfig.getCorePoolSize());

        assertNotEquals(null, threadPoolConfig.getKeepAliveTime());
        assertNotEquals(Long.valueOf(0), threadPoolConfig.getKeepAliveTime());
    }

    @Test
    public void checkFileNameWithScenariosWhenFileNameIsValid() {
        String expectedNameFile = "testScenarios.json";
        String currentNameFile = configHolder.getScenarioFile().getName();
        Assert.assertEquals(expectedNameFile, currentNameFile);
    }

    @Test
    public void checkFileNameWithScenariosWhenFileNameIsInvalid() {
        String expectedNameFile = "scenarios.json";
        String currentNameFile = configHolder.getScenarioFile().getName();
        Assert.assertNotEquals("File names are not equal!", expectedNameFile, currentNameFile);
    }

    @Test
    public void checkFieldsThreadPoolConfigReceivedFromFile() {
        Long keepAliveTime = 3L;
        Integer corePoolSize = 6;

        Assert.assertEquals(keepAliveTime, threadPoolConfig.getKeepAliveTime());
        Assert.assertEquals(corePoolSize, threadPoolConfig.getCorePoolSize());
    }

    @Test
    public void checkFieldsWebDriverConfigReceivedFromFile() {
        Long pageLoadTimeout = 30_000L;
        String webDriverExecutable = "resources/chromedriver";
        String userAgent = "desktop";

        Assert.assertEquals(pageLoadTimeout, webDriverConfig.getPageLoadTimeout());
        Assert.assertEquals(webDriverExecutable, webDriverConfig.getWebDriverExecutable());
        Assert.assertEquals(userAgent, webDriverConfig.getUserAgent());
    }
}