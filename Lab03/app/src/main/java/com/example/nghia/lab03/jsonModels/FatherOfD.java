package com.example.nghia.lab03.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Nghia on 11/27/2016.
 */

public class FatherOfD {
    @SerializedName("d")
    private SalonGroup[] d;

    public FatherOfD(SalonGroup[] d) {
        this.d = d;
    }

    public SalonGroup[] getD() {
        return d;
    }

    public void setD(SalonGroup[] d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "FatherOfD{" +
                "d=" + Arrays.toString(d) +
                '}';
    }
}
