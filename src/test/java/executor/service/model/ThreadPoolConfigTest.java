package executor.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ThreadPoolConfigTest {

    private ThreadPoolConfig threadPoolConfig;

    @Before
    public void setUp() {
        threadPoolConfig = new ThreadPoolConfig(1, 3L);
    }

    @Test
    public void testToString() {
        System.out.println(threadPoolConfig.toString());
        assertEquals("{\"corePoolSize\":1,\"keepAliveTime\":3}", threadPoolConfig.toString());
    }

    @Test
    public void testEquals() {
        ThreadPoolConfig other = new ThreadPoolConfig(1, 3L);
        assertEquals(other, threadPoolConfig);
        assertEquals(threadPoolConfig, other);

        other = new ThreadPoolConfig(1, 1L);
        assertNotEquals(other, threadPoolConfig);
        assertNotEquals(threadPoolConfig, other);

        other = new ThreadPoolConfig(3, 4L);
        assertNotEquals(other, threadPoolConfig);
        assertNotEquals(threadPoolConfig, other);


    }

    @Test
    public void testHashCode() {
        ThreadPoolConfig other = new ThreadPoolConfig(1, 3L);
        assertEquals(other.hashCode(), threadPoolConfig.hashCode());

        other = new ThreadPoolConfig(0, 3L);
        assertNotEquals(other.hashCode(), threadPoolConfig.hashCode());

        other = new ThreadPoolConfig(0, 0L);
        assertNotEquals(other.hashCode(), threadPoolConfig.hashCode());

        other = new ThreadPoolConfig(1, 0L);
        assertNotEquals(other.hashCode(), threadPoolConfig.hashCode());
    }
}