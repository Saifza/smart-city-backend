package com.smartcity.traffic.dto;

public class TrafficIncidentHeatmapDTO {
    private double lat;
    private double lng;
    private double intensity;

    public TrafficIncidentHeatmapDTO() {}

    public TrafficIncidentHeatmapDTO(double lat, double lng, double intensity) {
        this.lat = lat;
        this.lng = lng;
        this.intensity = intensity;
    }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public double getIntensity() { return intensity; }
    public void setIntensity(double intensity) { this.intensity = intensity; }
}
