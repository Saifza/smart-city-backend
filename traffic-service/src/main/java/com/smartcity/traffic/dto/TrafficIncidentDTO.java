package com.smartcity.traffic.dto;

import java.time.LocalDateTime;

import com.smartcity.traffic.model.Location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficIncidentDTO {

    @NotNull(message = "Location is required")
    private Location location;

    @NotBlank(message = "Severity is required")
    private String severity;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;
    
    @NotNull(message="City is required")
    private String city;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getSeverity() {
		return severity;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
    
}


