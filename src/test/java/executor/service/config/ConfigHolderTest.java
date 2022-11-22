package executor.service.config;

import executor.service.model.ThreadPoolConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class ConfigHolderTest {

    private ConfigHolder configHolder;

    @Before
    public void setUp() {
        configHolder = new ConfigHolder();
    }

    @Test
    public void threadPoolConfigTest() {
        ThreadPoolConfig threadPoolConfig = configHolder.getThreadPoolConfig();
        assertNotEquals(null, threadPoolConfig.getCorePoolSize());
        assertNotEquals(Integer.valueOf(0), threadPoolConfig.getCorePoolSize());

        assertNotEquals(null, threadPoolConfig.getKeepAliveTime());
        assertNotEquals(Long.valueOf(0), threadPoolConfig.getKeepAliveTime());
    }
}