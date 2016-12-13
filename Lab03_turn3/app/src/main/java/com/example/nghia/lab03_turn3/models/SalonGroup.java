package com.example.nghia.lab03_turn3.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nghia on 11/27/2016.
 */

public class SalonGroup {
    @SerializedName("d")
    List<Salon> salons;

    public SalonGroup(List<Salon> salons) {
        this.salons = salons;
    }

    public List<Salon> getSalons() {
        return salons;
    }

    public void setSalons(List<Salon> salons) {
        this.salons = salons;
    }

    @Override
    public String toString() {
        return "SalonGroup{" +
                "salons=" + salons +
                '}';
    }
}
