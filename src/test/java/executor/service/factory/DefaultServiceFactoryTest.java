package executor.service.factory;

import executor.service.service.DefaultScenarioSourceListener;
import executor.service.service.ScenarioSourceListener;
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
    public void create() throws IOException, InterruptedException {
        ScenarioSourceListener listener = factory.create(DefaultScenarioSourceListener.class);
        listener.execute();
    }
}