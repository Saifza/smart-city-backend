package com.smartcity;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.entity.EnergyUsage;
import com.smartcity.repository.EnergyUsageRepository;


@SpringBootApplication
public class EnergyServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnergyServiceApplication.class, args);
    }
    
   
    @Bean
    public CommandLineRunner runner(EnergyUsageRepository repo) {
        return args -> {
            EnergyUsage e1 = new EnergyUsage();
            e1.setConsumptionKwh(12435.0);
            e1.setTimestamp(LocalDateTime.now().minusHours(1));
            e1.setStatus("HIGH");

            EnergyUsage e2 = new EnergyUsage();
            e2.setConsumptionKwh(34255.0);
            e2.setTimestamp(LocalDateTime.now());
            e2.setStatus("Medium");
            
            EnergyUsage e3 = new EnergyUsage();
            e3.setConsumptionKwh(17200.0);
            e3.setTimestamp(LocalDateTime.now().plusHours(1));
            e3.setStatus("Medium");

            repo.save(e1);
            repo.save(e2);
            repo.save(e3);
        };
    }

 
   
   
    }  
   
