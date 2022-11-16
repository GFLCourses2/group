package executor.service.service;

import java.io.IOException;

public interface ScenarioSourceListener {
    void execute() throws IOException, InterruptedException;
}
