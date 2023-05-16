package ru.mirea.valkov.education.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*
        SPRING_DATASOURCE_PASSWORD=postgres; SPRING_DATASOURCE_USERNAME=postgres; SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgresdb;
        * */
        SpringApplication.run(Application.class, args);
    }
}
