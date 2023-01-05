package executor.service.config;

import executor.service.config.properties.ThreadPoolConfigProperties;
import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import executor.service.service.execution.executionservice.ScenarioExecutor;
import executor.service.service.execution.executionservice.worker.Worker;
import executor.service.service.holder.scenario.ScenarioHolder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@AllArgsConstructor
public class ParallelFlowExecutorServiceConfig {

    private ThreadPoolConfigProperties threadPoolConfigProperties;

    private ScenarioHolder scenarioHolder;

    private ScenarioExecutor scenarioExecutor;

    private WebDriverFactoryConfig webDriverFactoryConfig;


    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolConfigProperties.getCorePoolSize());
    }

    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(threadPoolConfigProperties.getCorePoolSize());
    }

    @Bean
    @Scope("prototype")
    public Worker worker() {
        return new Worker(scenarioHolder, scenarioExecutor, countDownLatch()) {
            @Override
            protected WebDriver getWebDriver() {
                return webDriverFactoryConfig.webDriver();
            }
        };
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
