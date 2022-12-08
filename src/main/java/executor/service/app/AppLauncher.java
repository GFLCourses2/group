package executor.service.app;

import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppLauncher implements CommandLineRunner {

    ParallelFlowExecutorService executorService;

    @Override
    public void run(String... args) {
        executorService.run();
    }

}
