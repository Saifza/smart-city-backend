package com.smartcity;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.entity.EnergyUsage;
import com.smartcity.repository.EnergyUsageRepository;


import jakarta.annotation.PostConstruct;


@SpringBootApplication
@EntityScan("com.smartcity.entity")
@EnableJpaRepositories("com.smartcity.repository")
public class EnergyServiceApplication {
    public static void main(String[] args) {
    	 System.out.println("🚀 Starting EnergyServiceApplication");
        SpringApplication.run(EnergyServiceApplication.class, args);
    }
    
   
      
    @Bean
    public CommandLineRunner runner(EnergyUsageRepository repo) {
        return args -> {
        	try {
				Thread.sleep(5000);
				System.out.println("⚡ CommandLineRunner executed!");
				repo.save(new EnergyUsage(null,12500.0, LocalDateTime.now().minusHours(1), "High"));

				repo.save(new EnergyUsage(null,34211.0, LocalDateTime.now(), "High"));     
				repo.save(new EnergyUsage(null, 17545.0, LocalDateTime.now().plusHours(1), "High"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        };
                    
    }
    
    
}      
       
   
   
