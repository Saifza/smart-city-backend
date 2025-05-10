package com.smartcity.emergency.entity;

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

    // Getters and setters
}
