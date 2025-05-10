package com.smartcity.emergency.entity;

import com.smartcity.emergency.entity.Location;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmergencyIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;         // e.g., Fire, Medical, Police
    private String severity;     // e.g., LOW, MEDIUM, HIGH
    private String description;
    private String city;

    @Embedded
    private Location location;

    private LocalDateTime timestamp;
    private String status;

    // Constructors
    public EmergencyIncident() {}

    public EmergencyIncident(Long id, String type, String severity, String description, String city, Location location, LocalDateTime timestamp, String status) {
        this.id = id;
        this.type = type;
        this.severity = severity;
        this.description = description;
        this.city = city;
        this.location = location;
        this.timestamp = timestamp;
        this.status=status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
