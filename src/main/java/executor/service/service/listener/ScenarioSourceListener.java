package executor.service.service.listener;

import executor.service.model.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface ScenarioSourceListener {
    Collection<Scenario> readScenarios(File source) throws IOException;
    void appendScenarios(File source, Collection<Scenario> scenarios) throws IOException;
}
