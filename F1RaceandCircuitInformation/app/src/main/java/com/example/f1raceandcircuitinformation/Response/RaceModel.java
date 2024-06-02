package com.example.f1raceandcircuitinformation.Response;

public class RaceModel {
    private String raceName;
    private String circuitImageUrl;
    private String locationImageUrl;

    public RaceModel(String raceName, String circuitImageUrl, String locationImageUrl) {
        this.raceName = raceName;
        this.circuitImageUrl = circuitImageUrl;
        this.locationImageUrl = locationImageUrl;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getCircuitImageUrl() {
        return circuitImageUrl;
    }

    public void setCircuitImageUrl(String circuitImageUrl) {
        this.circuitImageUrl = circuitImageUrl;
    }

    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }
}
