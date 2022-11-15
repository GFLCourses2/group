package executor.service.service;

import executor.service.model.Step;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Sleep implements StepExecution {

    @Override
    public String getStepAction() {
        return "Sleep";
    }

    @Override
    public void step(WebDriver webDriver, Step step) {
        long sleepTime = 3L;
        webDriver.manage().timeouts().implicitlyWait(sleepTime, TimeUnit.SECONDS);
    }
}