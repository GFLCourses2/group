package executor.service.factory;

import executor.service.factory.servicefactory.DefaultServiceFactory;
import executor.service.factory.servicefactory.ServiceFactory;
import executor.service.service.execution.executionservice.ParallelFlowExecutorService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DefaultServiceFactoryTest {
    private ServiceFactory factory;

    @Before
    public void setUp() {
        factory = new DefaultServiceFactory();
    }

    @Test
    public void create() {
        factory.create(ParallelFlowExecutorService.class);
    }
}