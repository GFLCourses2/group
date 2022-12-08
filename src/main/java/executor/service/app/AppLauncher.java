package executor.service.app;

import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppLauncher implements CommandLineRunner {

    ParallelFlowExecutorService executorService;

    @Override
    public void run(String... args) throws Exception {
        executorService.run();
    }

}
