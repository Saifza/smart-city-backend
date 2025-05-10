package com.smartcity.waste;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smartcity.waste.entity.Location;
import com.smartcity.waste.entity.WasteIncident;
import com.smartcity.waste.repository.WasteIncidentRepository;

@SpringBootApplication
public class WasteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner(WasteIncidentRepository repo) {
        return args -> {
            repo.save(new WasteIncident(
                    null,
                    "Overflowing Bin",
                    new Location(40.7128, -74.0060),
                    "REPORTED",
                    "Overflowing trash bin at street corner",
                    "New York",
                    LocalDateTime.now().minusHours(2)
            ));

            repo.save(new WasteIncident(
                    null,
                    "Illegal Dumping",
                    new Location(38.0293, -78.4767),
                    "IN_PROGRESS",
                    "Piles of construction debris reported",
                    "Charlottesville",
                    LocalDateTime.now().minusHours(1)
            ));

            repo.save(new WasteIncident(
                    null,
                    "Broken Dumpster",
                    new Location(43.0731, -89.4012),
                    "REPORTED",
                    "Dumpster door broken in back alley",
                    "Madison",
                    LocalDateTime.now()
            ));

            repo.save(new WasteIncident(
                    null,
                    "Littering",
                    new Location(39.0997, -94.5786),
                    "RESOLVED",
                    "Park area cleaned up after litter complaint",
                    "Kansas City",
                    LocalDateTime.now().minusDays(1)
            ));

            repo.save(new WasteIncident(
                    null,
                    "Overflowing Bin",
                    new Location(40.0150, -105.2705),
                    "REPORTED",
                    "Overflowing public bin near bus stop",
                    "Boulder",
                    LocalDateTime.now().minusMinutes(30)
            ));
            
            System.out.println("🗑️ Waste incidents pre-loaded.");
        };
    }
}

