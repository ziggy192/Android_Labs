package com.example.nghia.lab03.jsonModels;

/**
 * Created by Nghia on 11/27/2016.
 */

public class Image {
    private String url;
    private String thumb;
    private String description;
    private String title;

    public Image(String url, String thumb, String description, String title) {
        this.url = url;
        this.thumb = thumb;
        this.description = description;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    @Override
    public String toString() {
        return "\n\tImage{" +
                "url='" + url + '\'' +
                ", thumb='" + thumb + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
