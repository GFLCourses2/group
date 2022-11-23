package executor.service;

import executor.service.model.Scenario;
import executor.util.Scenarios;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Scenario sc1 = new Scenarios.ScenarioBuilder("http://info.cern.ch")
                .addStep("clickCss", "body > ul > li:nth-child(1) > a")
                .addStep("sleep", "5")
                .addStep("clickXpath", "/html/body/p")
                .build("test scenario 1");

        Scenario sc2 = new Scenarios.ScenarioBuilder("http://info.cern.ch")
                .addStep("clickXpath", "/html/body/p")
                .addStep("sleep", "5")
                .addStep("clickCss", "body > ul > li:nth-child(1) > a")
                .build("test scenario 2");

        new Scenarios.JsonBuilder()
                .add(sc1)
                .add(sc2)
                .buildFile("testScenarios");
    }
}
