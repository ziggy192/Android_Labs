package com.example.nghia.lab06.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/20/2016.
 */

public class Account {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
