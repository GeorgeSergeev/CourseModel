package org.bajiepka.courseApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        log.info("Запуск приложения...");
        SpringApplication.run(Application.class, args);
    }
}
