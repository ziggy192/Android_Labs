package com.example.nghia.lab06.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/20/2016.
 */

public class LoginResult {
    @SerializedName("result")
    private int result;
    @SerializedName("token")
    private String token;
    @SerializedName("message")
    private String message;

    public LoginResult(int result, String token, String message) {
        this.result = result;
        this.token = token;
        this.message = message;
    }


    public int getResult() {
        return result;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", result=" + result +
                '}';
    }
}
