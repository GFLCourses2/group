package executor.service.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.config.ConfigHolder;
import executor.service.model.Scenario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class DefaultScenarioSourceListener implements ScenarioSourceListener {

    private final ObjectMapper mapper;
    private final ConfigHolder configHolder;

    @Override
    public void appendScenarios(Collection<Scenario> scenarios) throws IOException {
        Collection<Scenario> read = mapper.readValue(
                configHolder.getScenarioFile(),
                mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class)
        );

        scenarios.addAll(read);
    }
}
