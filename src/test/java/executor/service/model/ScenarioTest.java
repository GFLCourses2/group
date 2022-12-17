package executor.service.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScenarioTest {
    @Test
    public void testEqualsAndHashCode() {
        Scenario scenario1 = new Scenario("Name",
                "Site",
                new ArrayList<>());
        Scenario scenario2 = new Scenario("Name",
                "Site",
                new ArrayList<>());
        assertEquals(scenario1, scenario2);
        assertEquals(scenario1.hashCode(), scenario2.hashCode());
    }


    @Test
    public void testToString() {
        String expectedString = "Scenario(name=Name, site=Site, steps=[Step(action=Action1, value=Value1)," +
                " Step(action=Action2, value=Value2)])";

        Scenario scenario = new Scenario();
        scenario.setName("Name");
        scenario.setSite("Site");
        List<Step> listSteps = new ArrayList<>();
        listSteps.add(new Step("Action1", "Value1"));
        listSteps.add(new Step("Action2", "Value2"));
        scenario.setSteps(listSteps);

        assertEquals(expectedString, scenario.toString());
    }
}