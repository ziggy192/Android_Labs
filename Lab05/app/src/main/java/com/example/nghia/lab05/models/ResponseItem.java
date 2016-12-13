package com.example.nghia.lab05.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Nghia on 12/11/2016.
 */

public class ResponseItem extends RealmObject {
    @SerializedName("Id")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("Thumb")
    private Image image;

    public ResponseItem() {
    }

    public ResponseItem(int id, String name, String description, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "ResponseItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
