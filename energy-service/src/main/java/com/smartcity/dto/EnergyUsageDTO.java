package com.smartcity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EnergyUsageDTO {

    @Min(value = 0, message = "Consumption must be >= 0")
    private double consumptionKwh;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;

    @NotBlank(message = "Status is required")
    private String status;

    
    
    
    public EnergyUsageDTO() {
		super();
	}

	public EnergyUsageDTO(@Min(value = 0, message = "Consumption must be >= 0") double consumptionKwh,
			@NotNull(message = "Timestamp is required") LocalDateTime timestamp,
			@NotBlank(message = "Status is required") String status) {
		super();
		this.consumptionKwh = consumptionKwh;
		this.timestamp = timestamp;
		this.status = status;
	}

	// Getters and setters
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
