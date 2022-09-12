package com.turganov.custom_url_shortener_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
public class CustomUrlShortenerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomUrlShortenerServiceApplication.class, args);



    }
}
