package com.smartcity.waste.dto;

import java.time.LocalDateTime;

public class WasteIncidentDTO {

    private Long id;
    private String type;
    private String status;
    private String city;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;

    public WasteIncidentDTO() {}

    public WasteIncidentDTO(Long id, String type, String status, String city, String description,
                            double latitude, double longitude, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.city = city;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
