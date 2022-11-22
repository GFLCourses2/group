package executor.service.service.listener;

import executor.service.model.Scenario;

import java.io.IOException;
import java.util.Collection;

public interface ScenarioSourceListener<S> {
    Collection<Scenario> getScenarios(S source) throws IOException;
}
