package executor.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:webDriverConfig.properties")
@Data
public class WebDriverConfigProperties {

    @Value("${executorservice.common.webDriverExecutable}")
    private String webDriverExecutable;

    @Value("${executorservice.common.userAgent}")
    private String userAgent;

    @Value("${executorservice.common.implicitlyWait}")
    private Long implicitlyWait;

    @Value("${executorservice.common.pageLoadTimeout}")
    private Long pageLoadTimeout;
}
