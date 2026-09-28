package com.cs590.webshop;

import com.cs590.webshop.service.WebShopRestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(WebShopRestService clientService) {
        return args -> {
            // Trigger client request workflow
            clientService.runDemo();
        };
    }
}
