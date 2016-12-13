package com.example.nghia.lab03_turn3.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nghia on 11/27/2016.
 */

public class Salon {
    @SerializedName("Description")
    private String description;
    @SerializedName("Title")
    private String title;
    @SerializedName("Images")
    private List<Image> images;
    @SerializedName("Id")
    private int Id;

    public Salon(String description, String title, List<Image> images, int id) {
        this.description = description;
        this.title = title;
        this.images = images;
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                ", Id=" + Id +
                '}';
    }
}
