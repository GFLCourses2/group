package executor.service.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class ProxyNetworkConfig {
    @JsonProperty("hostName")
    @JsonAlias("hostname")
    private String hostName;

    private Integer port;
}