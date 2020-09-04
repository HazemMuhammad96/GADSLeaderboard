package com.android.example.gadsleaderboard.models;

public class SkillLeader extends Leader {

    int score;

    public SkillLeader(String name, String country, String imageUrl, int score) {
        super(name, country, imageUrl);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return score + " Skill IQ Score, " + getCountry() + ".";
    }
}
