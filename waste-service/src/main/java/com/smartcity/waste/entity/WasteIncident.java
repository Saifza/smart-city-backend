package com.smartcity.waste.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WasteIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // e.g., "Overflow", "Illegal Dumping", "Bin Fire"

    @Embedded
    private Location location;

    private String status; // e.g., "REPORTED", "IN_PROGRESS", "RESOLVED"

    private String description;
    
    private String city;

    private LocalDateTime timestamp;

   
    
    

	public WasteIncident() {
		super();
	}

	public WasteIncident(Long id, String type, Location location, String status, String description,
			String city, LocalDateTime timestamp ) {
		super();
		this.id = id;
		this.type = type;
		this.location = location;
		this.status = status;
		this.description = description;
		this.city = city;
		this.timestamp = timestamp;
		
	}

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    
}
