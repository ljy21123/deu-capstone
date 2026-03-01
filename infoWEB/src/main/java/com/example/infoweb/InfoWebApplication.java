package com.example.infoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class InfoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoWebApplication.class, args);
    }

}
