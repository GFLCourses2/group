package executor.service.service.holder;

import executor.service.model.Scenario;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class DefaultScenarioHolder implements ScenarioHolder {

    private final Queue<Scenario> scenarios = new LinkedBlockingQueue<>();

    @Override
    public void addScenario(Scenario scenario) {
        scenarios.add(scenario);
    }

    @Override
    public synchronized Optional<Scenario> getScenario() {
        if (scenarios.isEmpty())
            return Optional.empty();

        return Optional.of(scenarios.poll());
    }
}
