package executor.service.service;

import executor.service.model.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
