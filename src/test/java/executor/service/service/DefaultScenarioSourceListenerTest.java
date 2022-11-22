package executor.service.service;

import executor.service.factory.DefaultServiceFactory;
import executor.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DefaultScenarioSourceListenerTest {

    private ScenarioSourceListener sourceListener;


    @Before
    public void setUp() {
        ServiceFactory factory = new DefaultServiceFactory();
        sourceListener = factory.create(ScenarioSourceListener.class);
    }

    @Test
    public void execute() throws IOException, InterruptedException {
        sourceListener.execute();
    }
}