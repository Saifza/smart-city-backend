package com.smartcity.emergency;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.smartcity.emergency.entity.EmergencyIncident;
import com.smartcity.emergency.repository.EmergencyIncidentRepository;
import com.smartcity.emergency.entity.Location;


@SpringBootApplication
@EnableScheduling
public class EmergencyServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmergencyServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner(EmergencyIncidentRepository repo) {
        return args -> {
            repo.save(new EmergencyIncident(null, "Fire", "HIGH", "Building fire downtown", "Kansas City", new Location(39.0997, -94.5786), LocalDateTime.now().minusHours(2), "Active"));
            repo.save(new EmergencyIncident(null, "Medical", "MEDIUM", "Heart attack at Eastside", "Kansas City", new Location(39.1030, -94.5722), LocalDateTime.now().minusMinutes(30), "Resolved"));
            repo.save(new EmergencyIncident(null, "Crime", "HIGH", "Armed robbery in West Park", "Kansas City", new Location(39.0850, -94.6002), LocalDateTime.now(), "Investigating"));
            repo.save(new EmergencyIncident(null, "Fire", "HIGH", "Wildfire near Boulder outskirts", "Boulder", new Location(40.0200, -105.2700), LocalDateTime.now().minusHours(3), "Contained"));
            repo.save(new EmergencyIncident(null, "Medical", "LOW", "Multiple injuries from crash", "Madison", new Location(43.0731, -89.4012), LocalDateTime.now().minusHours(1), "Active"));
            repo.save(new EmergencyIncident(null, "Crime", "MEDIUM", "Burglary in Charlottesville neighborhood", "Charlottesville", new Location(38.0293, -78.4767), LocalDateTime.now().minusMinutes(10), "Ongoing"));
        };
    }



}

