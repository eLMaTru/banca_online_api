package com.bancaonline.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * banca online api
 *
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see <a href="http://swagger.io">Swagger</a>
 * @see <a href="https://projects.spring.io/spring-boot/">Spring boot</a>
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableScheduling
@EnableAsync
public class Application {

    /**
     * The main method of the class which run the spring boot application
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
