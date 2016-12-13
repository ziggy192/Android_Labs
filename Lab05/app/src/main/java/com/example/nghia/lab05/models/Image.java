package com.example.nghia.lab05.models;

import io.realm.RealmObject;

/**
 * Created by Nghia on 12/11/2016.
 */

public class Image extends RealmObject {
    private String url;
    private String thumb;
    private String title;
    private String description;

    public Image() {
    }

    public Image(String url, String thumb, String title, String description) {
        this.url = url;
        this.thumb = thumb;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                ", thumb='" + thumb + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
