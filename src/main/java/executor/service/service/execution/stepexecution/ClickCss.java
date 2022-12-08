package executor.service.service.execution.stepexecution;

import executor.service.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ClickCss")
public class ClickCss implements StepExecution {
    @Override
    public String getStepAction() {
        return "clickcss";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        WebElement element = webDriver.findElement(By.ByCssSelector.cssSelector(step.getValue()));
        element.click();
    }
}
