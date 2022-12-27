package executor.service.app;

import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppLauncher implements CommandLineRunner {
    private final ParallelFlowExecutorService parallelFlowExecutorService;
    @Override
    public void run(String... args) throws Exception {
        parallelFlowExecutorService.run();
    }
}
