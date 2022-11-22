package executor.service.service.execution.executionservice;

import java.io.IOException;

public interface ExecutionService<S> {
    void execute(S source) throws IOException, InterruptedException;
}
