package com.example.f1raceandcircuitinformation.Response;

public class HistoryItem {
    private int id;
    private String name;
    private String imageUrl;
    private String date;
    private String lastSeen;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    private  String Type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public HistoryItem(int id, String name, String imageUrl, String date) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.date = date;
    }

}
