package com.github.sparkWeb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Calculation {

    @Id
    private long id;
    private double stat1;

    public Calculation(long id, double stat1) {
        this.id = id;
        this.stat1 = stat1;
    }

    public Calculation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStat1() {
        return stat1;
    }

    public void setStat1(double stat1) {
        this.stat1 = stat1;
    }
}
