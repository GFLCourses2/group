package executor.service.service;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.model.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DefaultScenarioSourceListener implements ScenarioSourceListener {
    private final WebDriver driver;
    private final File scenarioFile;
    private final ScenarioExecutor executor;

    public DefaultScenarioSourceListener(WebDriver webDriver,
                                         File scenarioFile,
                                         ScenarioExecutor executor) {
        this.driver = webDriver;
        this.scenarioFile = scenarioFile;
        this.executor = executor;
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        Scenario[] scenarios = getScenarios();
        for (Scenario s : scenarios) {
            executor.execute(s, driver);
        }
    }

    private Scenario[] getScenarios() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(scenarioFile, Scenario[].class);
        } catch (DatabindException de) {
            Collection<Scenario> sl = mapper.readValue(scenarioFile,
                    mapper.getTypeFactory().constructCollectionType(Collection.class, Scenario.class));
            return sl.toArray(new Scenario[0]);
        }
    }
}
