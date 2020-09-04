package com.android.example.gadsleaderboard.models;

public class LearningLeader extends Leader {

    int hours;

    public LearningLeader(String name, String country, String imageUrl, int hours) {
        super(name, country, imageUrl);
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return this.hours + " Learning Hours, " + getCountry() + ".";
    }
}
