package com.smartcity.emergency.dto;

import com.smartcity.emergency.entity.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class EmergencyIncidentDTO {

    @NotBlank
    private String type;

    @NotBlank
    private String severity;

    @NotBlank
    private String description;

    @NotBlank
    private String city;

    @NotNull
    private Location location;

    @NotNull
    private LocalDateTime timestamp;
    
    @NotBlank
    private String status;

    // Constructors
    public EmergencyIncidentDTO() {}

    public EmergencyIncidentDTO(String type, String severity, String description, String city, Location location, LocalDateTime timestamp, String status) {
        this.type = type;
        this.severity = severity;
        this.description = description;
        this.city = city;
        this.location = location;
        this.timestamp = timestamp;
        this.status=status;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

