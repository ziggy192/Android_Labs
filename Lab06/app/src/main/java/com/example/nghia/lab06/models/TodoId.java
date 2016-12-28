package com.example.nghia.lab06.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/20/2016.
 */

public class TodoId {
    @SerializedName("$oid")
    private String id;

    public TodoId(String id) {
        this.id = id;
    }

    public TodoId() {
        id = null;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TodoId{" +
                "id='" + id + '\'' +
                '}';
    }
}
