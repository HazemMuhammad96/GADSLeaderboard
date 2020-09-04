package com.android.example.gadsleaderboard.models;

public class Leader {

    private String name, country, badgeUrl;


    public Leader(String name, String country, String imageUrl) {
        this.name = name;
        this.country = country;
        badgeUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
