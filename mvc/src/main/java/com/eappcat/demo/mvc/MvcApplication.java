package com.eappcat.demo.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

    @Bean
    ExecutorService executorService(){
        return Executors.newSingleThreadExecutor();
    }

}
