package executor.service.controller;

import executor.service.model.Scenario;
import executor.service.service.holder.ScenarioHolder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ScenarioController {

    private ScenarioHolder scenarioHolder;

    @PostMapping(value = "/scenario/add", consumes = "application/json")
    public ResponseEntity<?> addScenario(@ModelAttribute Scenario scenario) {
        scenarioHolder.addScenario(scenario);
        return ResponseEntity.ok("Added successfully");
    }
}
