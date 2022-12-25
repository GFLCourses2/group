package executor.service.service.request.scenario;

import executor.service.config.RequestConfig;
import executor.service.service.holder.scenario.ScenarioHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RequestScenario {
    private final ScenarioHolder scenarioHolder;

    private final RequestConfig requestConfig;

    public RequestScenario(ScenarioHolder scenarioHolder, RequestConfig requestConfig) {
        this.scenarioHolder = scenarioHolder;
        this.requestConfig = requestConfig;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void request() {
    }
}
