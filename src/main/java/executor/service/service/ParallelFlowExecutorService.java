package executor.service.service;

import executor.service.config.ConfigHolder;
import executor.service.model.Scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ParallelFlowExecutorService {
    private final ConfigHolder configHolder = new ConfigHolder();
    private final ExecutionService executionService;
    private final ScenarioSourceListener scenarioListener;
    private final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(configHolder.getThreadPoolConfig().getCorePoolSize());
    private final Queue<Scenario> scenarioQueue = new LinkedBlockingQueue<>();

    public ParallelFlowExecutorService(ExecutionService executionService, ScenarioSourceListener scenarioListener) {
        this.executionService = executionService;
        this.scenarioListener = scenarioListener;
    }

    public void execute() throws IOException {
        initScenarioQueue();

        try {
            for (int i = 0; i < scenarioQueue.size(); i++) {
                Scenario scenario = scenarioQueue.poll();
                Runnable runnable = () -> {
                    try {
                        executionService.execute(scenario);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                };
                threadPoolExecutor.execute(runnable);
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private void initScenarioQueue() throws IOException {
        List<Scenario> scenarios = new ArrayList<>(scenarioListener.getScenarios(configHolder.getScenarioFile()));
        scenarioQueue.addAll(scenarios);
    }
}