package executor.service.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/threadPoolConfig.properties")
@Data
public class ThreadPoolConfigProperties {
    @Value("${executorservice.common.corePoolSize}")
    private Integer corePoolSize;

    @Value("${executorservice.common.keepAliveTime}")
    private Long keepAliveTime;
}