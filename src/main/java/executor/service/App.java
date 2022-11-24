package executor.service;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.service.execution.executionservice.ParallelFlowExecutorService;

public class App {
    public static void main(String[] args) {
        ServiceFactory serviceFactory = new DefaultServiceFactory();
        ParallelFlowExecutorService executorService = serviceFactory.create(ParallelFlowExecutorService.class);
        executorService.run();
    }
}
