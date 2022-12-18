package executor.service.controller;

import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.holder.ScenarioHolder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ScenarioController {

    private ScenarioHolder scenarioHolder;

    @GetMapping(value = "/scenario/get/test")
    public ResponseEntity<Scenario> returnTestScenario() {
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("сlickсss", "body > div > div.td-main > div > main > nav > ol > li:nth-child(1) > a"));
        return ResponseEntity.ok(new Scenario("name", "https://localhost", steps));
    }

    @PostMapping(value = "/scenario/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addScenario(@RequestBody Scenario scenario) {
        scenarioHolder.addScenario(scenario);
        return ResponseEntity.ok("Added successfully");
    }
}
