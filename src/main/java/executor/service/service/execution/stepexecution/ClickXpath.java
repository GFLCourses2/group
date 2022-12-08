package executor.service.service.execution.stepexecution;

import executor.service.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ClickXpath")
public class ClickXpath implements StepExecution {
    @Override
    public String getStepAction() {
        return "clickXpath";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        WebElement webElement = webDriver.findElement(By.xpath(step.getValue()));
        webElement.click();
    }
}
