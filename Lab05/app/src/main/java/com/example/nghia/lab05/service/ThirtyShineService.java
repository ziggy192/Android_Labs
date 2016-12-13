package com.example.nghia.lab05.service;

import com.example.nghia.lab05.models.ThirtyShineRequestBody;
import com.example.nghia.lab05.models.ThirtyShineResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Nghia on 12/11/2016.
 */

public interface ThirtyShineService {
    @POST("home")
    Call<ThirtyShineResponseBody> postToGetData(@Body ThirtyShineRequestBody thirtyShineRequestBody);

}
