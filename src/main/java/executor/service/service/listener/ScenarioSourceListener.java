package executor.service.service.listener;

import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ScenarioSourceListener {
    void appendScenarios(Collection<Scenario> scenarios) throws IOException;
}
