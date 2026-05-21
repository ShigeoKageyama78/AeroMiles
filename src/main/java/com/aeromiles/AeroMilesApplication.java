package com.aeromiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AeroMilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeroMilesApplication.class, args);
        System.out.println("AeroMiles Tracker démarré sur http://localhost:8080");
    }
}
