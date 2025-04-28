package com.smartcity.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class EnergyUsage {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
        
	    @NotNull(message = "Consumption is required")
	    @Min(value = 0, message = "Consumption must be >= 0")
	    private double consumptionKwh;
        
	    @NotNull(message = "Timestamp is required")
	    private LocalDateTime timestamp;

	    @NotBlank(message = "Status is required")
	    private String status;
	    
	    

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getConsumptionKwh() {
			return consumptionKwh;
		}

		public void setConsumptionKwh(double consumptionKwh) {
			this.consumptionKwh = consumptionKwh;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    

}
