package executor.service.service.execution.stepexecution;

import executor.service.model.Step;
import org.openqa.selenium.WebDriver;

public interface StepExecution {
    String getStepAction();

    void step(WebDriver webDriver, Step step);
}
