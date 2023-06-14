package com.example.driverarlet;

public class TripModelClass {

    String distance;
    String from;
    String to;


    public TripModelClass(String distance, String from, String to) {
        this.distance = distance;
        this.from = from;
        this.to = to;
    }

    public TripModelClass() {
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
