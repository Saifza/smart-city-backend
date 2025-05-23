package com.smartcity.waste.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    private double latitude;
    private double longitude;

    public Location() {}
    public Location(double lat, double lng) {
        this.latitude = lat;
        this.longitude = lng;
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

    
}
