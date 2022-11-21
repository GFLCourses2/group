package executor.service.service;

import java.io.IOException;

public interface ExecutionService<S> {
    void execute(S source) throws IOException, InterruptedException;
}
