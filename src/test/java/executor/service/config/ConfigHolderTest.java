package executor.service.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigHolderTest {

    private ConfigHolder configHolder;

    @Before
    public void setUp() {
        configHolder = new ConfigHolder();
    }

    @Test
    public void checkFileNameWithScenariosWhenFileNameIsValid() {
        String expectedNameFile = "testScenarios.json";
        String currentNameFile = configHolder.getScenarioFile().getName();
        Assert.assertEquals(expectedNameFile, currentNameFile);
    }

    @Test
    public void checkFileNameWithScenariosWhenFileNameIsInvalid() {
        String expectedNameFile = "scenarios.json";
        String currentNameFile = configHolder.getScenarioFile().getName();
        Assert.assertNotEquals("File names are not equal!", expectedNameFile, currentNameFile);
    }
}