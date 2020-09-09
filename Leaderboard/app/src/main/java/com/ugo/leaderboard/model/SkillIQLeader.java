package com.ugo.leaderboard.model;

public class SkillIQLeader {
    private String name;
    private int score;
    private String country;

    public SkillIQLeader(String name, int score, String country) {
        this.name = name;
        this.score = score;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }
}
