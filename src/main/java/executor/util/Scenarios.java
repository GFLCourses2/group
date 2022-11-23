package executor.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;
import executor.service.model.Step;
import executor.service.service.execution.stepexecution.StepExecution;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Scenarios {
    /**
     * Scenario builder
     */
    public static class ScenarioBuilder {
        private final String url;

        private String name;
        private final List<Step> steps = new ArrayList<>();

        public ScenarioBuilder(String url) {
            this.url = url;
        }

        public ScenarioBuilder addStep(Step step) {
            steps.add(step);
            return this;
        }

        public ScenarioBuilder addStep(String action, String value) {
            steps.add(new Step(action, value));
            return this;
        }

        public ScenarioBuilder removeStep(Step step) {
            steps.remove(step);
            return this;
        }

        public ScenarioBuilder removeStep(String action, String value) {
            steps.remove(new Step(action, value));
            return this;
        }

        public Scenario build(String name) {
            return new Scenario(name, url, steps);
        }
    }

    /**
     * Util for create jason list of scenarios
     */
    public static class JsonBuilder {

        private final Collection<Scenario> scenarios = new ArrayList<>();

        public JsonBuilder add(Scenario scenario) {
            scenarios.add(scenario);
            return this;
        }

        public JsonBuilder remove(Scenario scenario) {
            scenarios.remove(scenario);
            return this;
        }

        public String buildString() throws JsonProcessingException {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(scenarios);
        }

        /**
         * Creates a file (in a directory from the project root named "scenarios")
         * with a JSON representation of a list of scenarios.
         *
         * @param fileName WITHOUT EXTENSION
         */
        public void buildFile(String fileName) throws IOException {
            File dir = new File("scenarios");
            dir.mkdir();
            String name = fileName + ".json";
            File file = new File(dir, name);

            new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(file, scenarios);
        }
    }
}
