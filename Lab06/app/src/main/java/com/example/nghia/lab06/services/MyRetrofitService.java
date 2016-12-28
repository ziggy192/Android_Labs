package com.example.nghia.lab06.services;


import com.example.nghia.lab06.models.Account;
import com.example.nghia.lab06.models.LoginResult;
import com.example.nghia.lab06.models.RegisterResult;
import com.example.nghia.lab06.models.TodoModel;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Nghia on 12/10/2016.
 */

public interface MyRetrofitService {
    @POST("login")
    Call<LoginResult> startLogin(@Body Account account);

    @POST("register")
    Call<RegisterResult> startRegister(@Body Account account);

    @GET("todos")
    Call<ArrayList<TodoModel>> getUser(@HeaderMap Map<String, String> headers);

    @POST("todos")
    Call<TodoModel[]> createTodo(@Body TodoModel todoModel, @HeaderMap Map<String, String> headers);

    @PUT("todos/{todo_id}")
    Call<TodoModel> editTodo(@Body TodoModel editedTodoModel, @Path("todo_id") String todoId
            , @HeaderMap Map<String, String> headers);


}
