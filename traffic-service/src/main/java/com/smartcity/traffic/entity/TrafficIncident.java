package com.smartcity.traffic.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.smartcity.traffic.model.Location;

@Entity
public class TrafficIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Location location;

    private String severity; // LOW, MEDIUM, HIGH

    private String description;

    private LocalDateTime timestamp;
    private String city; 
    
    

    

	public TrafficIncident() {
    	
    	
    }

    public TrafficIncident(Long id, Location location, String severity, String description, LocalDateTime timestamp, String city) {
        this.id = id;
        this.location = location;
        this.severity = severity;
        this.description = description;
        this.timestamp = timestamp;
        this.city=city;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
	    return location;
	}

	public void setLocation(Location location) {
	    this.location = location;
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
