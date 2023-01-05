package executor.service.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.ProxyConfigHolder;
import executor.service.model.Scenario;
import executor.service.service.holder.proxy.ProxyHandler;
import executor.service.service.holder.scenario.ScenarioHolder;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneralVPNListener {

    private final ObjectMapper objectMapper;

    private final ProxyHandler proxyHandler;

    private final ScenarioHolder scenarioHolder;

    @JmsListener(destination = "proxy")
    public void listenProxy(String proxyJson) {
        try {
            ProxyConfigHolder proxyConfigHolder = objectMapper.readValue(proxyJson, ProxyConfigHolder.class);
            proxyHandler.addProxy(proxyConfigHolder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @JmsListener(destination = "scenario")
    public void listenScenario(String scenarioJson) {
        try {
            Scenario scenario = objectMapper.readValue(scenarioJson, Scenario.class);
            scenarioHolder.addScenario(scenario);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
