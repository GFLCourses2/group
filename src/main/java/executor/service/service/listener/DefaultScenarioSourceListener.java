package executor.service.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Service
public class DefaultScenarioSourceListener implements ScenarioSourceListener {

    private final ObjectMapper mapper;
    private final File scenarioFile = new File("testScenarios.json");

    public DefaultScenarioSourceListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void appendScenarios(Collection<Scenario> scenarios) throws IOException {
        Collection<Scenario> read = mapper.readValue(
                scenarioFile,
                mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class)
        );

        scenarios.addAll(read);
    }
}
