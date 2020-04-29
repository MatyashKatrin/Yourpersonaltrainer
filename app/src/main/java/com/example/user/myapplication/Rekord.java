package com.example.user.myapplication;

public class Rekord {
    private String id;
    private String date;
    private String distance;
    public Rekord() {
    }
    public Rekord(String id, String date, String distance) {
        this.id = id;
        this.date = date;
        this.distance = distance;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

