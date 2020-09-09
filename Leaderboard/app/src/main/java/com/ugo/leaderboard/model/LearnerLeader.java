package com.ugo.leaderboard.model;

public class LearnerLeader {
    private String name;
    private int hours;
    private String country;

    public LearnerLeader(String name, int hours, String country) {
        this.name = name;
        this.hours = hours;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }
}
