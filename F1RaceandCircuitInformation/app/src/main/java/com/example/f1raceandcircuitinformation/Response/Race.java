package com.example.f1raceandcircuitinformation.Response;

import com.google.gson.annotations.SerializedName;

public class Race {
    private String season;
    private String round;
    private String url;
    private String raceName;
    @SerializedName("Circuit")
    private Circuit circuit;
    private String date;
    private String time;
    @SerializedName("FirstPractice")
    private Practice firstPractice;
    @SerializedName("SecondPractice")
    private Practice secondPractice;
    @SerializedName("ThirdPractice")
    private Practice thirdPractice;
    @SerializedName("Qualifying")
    private Qualifying qualifying;

    // Getters and setters

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Practice getFirstPractice() {
        return firstPractice;
    }

    public void setFirstPractice(Practice firstPractice) {
        this.firstPractice = firstPractice;
    }

    public Practice getSecondPractice() {
        return secondPractice;
    }

    public void setSecondPractice(Practice secondPractice) {
        this.secondPractice = secondPractice;
    }

    public Practice getThirdPractice() {
        return thirdPractice;
    }

    public void setThirdPractice(Practice thirdPractice) {
        this.thirdPractice = thirdPractice;
    }

    public Qualifying getQualifying() {
        return qualifying;
    }

    public void setQualifying(Qualifying qualifying) {
        this.qualifying = qualifying;
    }
}


