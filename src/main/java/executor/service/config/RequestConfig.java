package executor.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/request.properties")
@Data
public class RequestConfig {
    @Value("${request.fixedRate}")
    private long fixedRate;
}
