package executor.service.service.request.scenario;

import executor.service.config.RequestScenarioConfig;
import executor.service.service.holder.scenario.ScenarioHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DefaultRequestScenario implements RequestScenario {
    private final ScenarioHolder scenarioHolder;

    private final RequestScenarioConfig requestScenarioConfig;

    public DefaultRequestScenario(ScenarioHolder scenarioHolder, RequestScenarioConfig requestScenarioConfig) {
        this.scenarioHolder = scenarioHolder;
        this.requestScenarioConfig = requestScenarioConfig;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    @Async
    public void request() {
    }
}
