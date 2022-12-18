package executor.service.config;

import executor.service.factory.webdriver.WebDriverFactory;
import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import executor.service.service.execution.executionservice.ScenarioExecutor;
import executor.service.service.execution.executionservice.worker.Worker;
import executor.service.service.holder.ScenarioHolder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@AllArgsConstructor
public class ParallelFlowExecutorServiceConfig {

    private ThreadPoolConfig threadPoolConfig;

    private ScenarioHolder scenarioHolder;

    private ScenarioExecutor scenarioExecutor;

    private WebDriverFactory webDriverFactory;


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolConfig.getCorePoolSize());
    }
    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(threadPoolConfig.getCorePoolSize());
    }

    @Bean
    @Scope("prototype")
    public Worker worker() {
        return new Worker(scenarioHolder, scenarioExecutor, webDriverFactory, countDownLatch());
    }

    @Bean
    public ParallelFlowExecutorService parallelFlowExecutorService() {
        return new ParallelFlowExecutorService(threadPoolExecutor(), countDownLatch()) {
            @Override
            protected Worker getWorker() {
                return worker();
            }
        };
    }

}
