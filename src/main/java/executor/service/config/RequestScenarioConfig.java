package executor.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/requestScenario.properties")
@Data
public class RequestScenarioConfig {
    @Value("${request.scenario.fixedRate}")
    private long fixedRate;
}
