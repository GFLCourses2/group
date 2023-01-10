package executor.service.service.holder.scenario;

import executor.service.model.Scenario;

import java.util.Optional;

public interface ScenarioHolder {
    void addScenario(Scenario scenario);

    Optional<Scenario> getScenario();
}
