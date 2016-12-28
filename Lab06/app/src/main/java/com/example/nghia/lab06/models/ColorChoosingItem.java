package com.example.nghia.lab06.models;

/**
 * Created by Nghia on 12/24/2016.
 */

public class ColorChoosingItem {
    private String colorName;
    private String colorString;

    public ColorChoosingItem(String colorName, String colorString) {
        this.colorName = colorName;
        this.colorString = colorString;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorString() {
        return colorString;
    }

    @Override
    public String toString() {
        return this.colorName;
    }

    public static final ColorChoosingItem[] colors = {
            new ColorChoosingItem("Yellow", "#FFFF00")
            , new ColorChoosingItem("Green", "#76FF03")
            , new ColorChoosingItem("Cyan", "#18FFFF")
            , new ColorChoosingItem("Pink", "#FF00FF")
    };
}
