package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class TgBotApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(TgBotApplication.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
