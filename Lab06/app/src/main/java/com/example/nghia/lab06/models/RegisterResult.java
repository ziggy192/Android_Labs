package com.example.nghia.lab06.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nghia on 12/20/2016.
 */

public class RegisterResult {
    @SerializedName("result")
    private int result;
    @SerializedName("message")
    private String message;

    public RegisterResult(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
