package executor.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class ProxyCredentials {
    private String username;
    private String password;
}
