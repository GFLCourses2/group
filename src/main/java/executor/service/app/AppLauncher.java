package executor.service.app;

import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppLauncher implements CommandLineRunner {

    private final ParallelFlowExecutorService executorService;

    public AppLauncher(ParallelFlowExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) {
        executorService.run();
    }
}