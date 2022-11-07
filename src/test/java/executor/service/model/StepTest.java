package executor.service.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class StepTest {

    @Test
    public void testEquals() {
        Step step = new Step("Do step", "one");
        Step sameStep = new Step("Do step", "one");
        assertEquals(step, sameStep);
        assertTrue(step.equals(sameStep));
    }

    @Test
    public void testHashCode() {
        int expectedHash = -990011047;
        Step step = new Step("Do step", "one");
        assertEquals(expectedHash, step.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "{\"action\":\"Do step\",\"value\":\"one\"}";
        
        Step step = new Step("Do step", "one");
        assertEquals(expectedString, step.toString());
    }

}
