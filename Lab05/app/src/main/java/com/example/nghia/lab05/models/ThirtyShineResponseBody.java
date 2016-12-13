package com.example.nghia.lab05.models;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nghia on 12/11/2016.
 */

public class ThirtyShineResponseBody extends RealmObject {


    private static final int DEFAULT_REALM_ID_KEY = 1;
    @SerializedName("d")
    private RealmList<ResponseItem> responseItems;

    public ThirtyShineResponseBody() {
    }

    public ThirtyShineResponseBody(RealmList<ResponseItem> responseItems) {
        this.responseItems = responseItems;
    }

    public RealmList<ResponseItem> getResponseItems() {
        return responseItems;
    }


    @Override
    public String toString() {
        return "ThirtyShineResponseBody{" +
                "responseItems=" + responseItems +
                '}';
    }
}
