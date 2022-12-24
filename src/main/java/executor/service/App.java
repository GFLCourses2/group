package executor.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        try {
            SpringApplication.run(App.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
