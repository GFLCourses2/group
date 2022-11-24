package executor.service.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DefaultScenarioSourceListener implements ScenarioSourceListener {

    @Override
    public Collection<Scenario> readScenarios(File source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                source,
                mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class)
        );
    }

    @Override
    public void appendScenarios(File source, Collection<Scenario> scenarios) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Collection<Scenario> read = mapper.readValue(
                source,
                mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class)
        );

        scenarios.addAll(read);
    }
}
