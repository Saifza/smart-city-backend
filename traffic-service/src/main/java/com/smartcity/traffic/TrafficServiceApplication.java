package com.smartcity.traffic;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.smartcity.traffic.entity.TrafficIncident;
import com.smartcity.traffic.repository.TrafficIncidentRepository;
import com.smartcity.traffic.model.Location;

@SpringBootApplication
@EnableScheduling 
public class TrafficServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrafficServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner(TrafficIncidentRepository repo) {
        return args -> {
            repo.save(new TrafficIncident(
                    null,
                    new Location(40.7128, -74.0060), // Example coordinates for "Main St"
                    "HIGH",
                    "Multi-car crash",
                    LocalDateTime.now().minusHours(1), 
                    "Kansas City"
            ));

            repo.save(new TrafficIncident(
                    null,
                    new Location(40.7138, -74.0050), // Example coordinates for "2nd Ave"
                    "LOW",
                    "Slow traffic due to rain",
                    LocalDateTime.now(),
                    "Kansas City"
            ));
        };
    }

}

