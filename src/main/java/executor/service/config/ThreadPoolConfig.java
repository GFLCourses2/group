package executor.service.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static executor.utils.PropertiesHelper.*;

@Component(ThreadPoolConfig.BEAN_NAME)
@Data
public class ThreadPoolConfig {
    public static final String BEAN_NAME = "properties_ThreadPoolConfig";

    private static final String CONFIG_PATH = "src/main/java/resources/threadPoolConfig.properties";

    private static final String POOL_SIZE_KEY = "executorservice.common.corePoolSize";
    private static final String ALIVE_TIME_KEY = "executorservice.common.keepAliveTime";

    private static final int POOL_SIZE_DEF_VALUE = 1;
    private static final long ALIVE_TIME_DEF_VALUE = 3L;

    private Integer corePoolSize;
    private Long keepAliveTime;

    @PostConstruct
    private void init() {
        Properties properties = readProperties(CONFIG_PATH);

        this.corePoolSize = getPropertyIntValue(properties, POOL_SIZE_KEY, POOL_SIZE_DEF_VALUE);
        this.keepAliveTime = getPropertyLongValue(properties, ALIVE_TIME_KEY, ALIVE_TIME_DEF_VALUE);
    }
}