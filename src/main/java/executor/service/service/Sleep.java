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
        try {
            TimeUnit.SECONDS.sleep(Long.parseLong(step.getValue()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception: " + e.getMessage());
        }
    }
}