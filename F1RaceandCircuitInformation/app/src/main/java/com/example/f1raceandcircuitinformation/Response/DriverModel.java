package com.example.f1raceandcircuitinformation.Response;

public class DriverModel {
    private String name;
    private String imageUrl;
    private String flagUrl;

    public DriverModel(String name, String imageUrl, String flagUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.flagUrl = flagUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}

