package com.github.sparkWeb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Averages {

    @Id
    private long id;
    private String state;
    private double average;

    public Averages(long id, String state, double average) {
        this.id = id;
        this.state = state;
        this.average = average;
    }

    public Averages(String state, double average) {
        this.state = state;
        this.average = average;
    }

    public Averages() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
