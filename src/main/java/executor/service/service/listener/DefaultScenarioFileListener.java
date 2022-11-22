package executor.service.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DefaultScenarioFileListener implements ScenarioFileListener {

    @Override
    public Collection<Scenario> getScenarios(File source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                source,
                mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class)
        );
    }
}
