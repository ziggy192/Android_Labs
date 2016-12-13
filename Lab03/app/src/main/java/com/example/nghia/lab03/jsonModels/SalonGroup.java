package com.example.nghia.lab03.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Nghia on 11/27/2016.
 */

public class SalonGroup {
    @SerializedName("d")
    Salon[] salons;

    public SalonGroup(Salon[] salons) {
        this.salons = salons;
    }

    public Salon[] getSalons() {
        return salons;
    }

    public void setSalons(Salon[] salons) {
        this.salons = salons;
    }

    @Override
    public String toString() {
        return "SalonGroup{" +
                "\n"+"salons=" + Arrays.toString(salons) +
                '}';
    }
}
