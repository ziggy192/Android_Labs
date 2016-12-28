package com.example.nghia.lab06.utils;

import android.util.Log;

import com.example.nghia.lab06.events.OnGetTodoListEvent;
import com.example.nghia.lab06.events.OnReceiveNewTodoEvent;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.services.MyRetrofitService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nghia on 12/24/2016.
 */

public class ServiceContext {
    final String HEADER_TOKEN_KEY = "token";
    private static final String TAG = ServiceContext.class.toString();
    private Retrofit retrofit;

    private ServiceContext(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  ServiceContext instance ;
    public static void init(String baseUrl){
        instance = new ServiceContext(baseUrl);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static ServiceContext getInstance() {
        return instance;
    }

    public void getTodos(String token) {

        MyRetrofitService service = getRetrofit().create(MyRetrofitService.class);
        HashMap<String, String> headers = new HashMap<>();
        headers.put(HEADER_TOKEN_KEY, token);
        Call<ArrayList<TodoModel>> call=  service.getUser(headers);
        call.enqueue(new Callback<ArrayList<TodoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoModel>> call, Response<ArrayList<TodoModel>> response) {
                ArrayList<TodoModel> todoModels = response.body();
//                Log.d(TAG, String.format("onResponse: todoModels = %s", todoModels));
                OnGetTodoListEvent event = new OnGetTodoListEvent(todoModels);
                EventBus.getDefault().post(event);
//                updateUI(todoModels);
            }

            @Override
            public void onFailure(Call<ArrayList<TodoModel>> call, Throwable t) {

            }
        });
    }

    public void editTodo(String token, final TodoModel editedTodoModel, final ServiceTodoReceivedListener listener) {
        MyRetrofitService service = getRetrofit().create(MyRetrofitService.class);
        HashMap<String, String> headers = new HashMap<>();
        headers.put(HEADER_TOKEN_KEY, token);
//        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Call<TodoModel> call = service.editTodo(editedTodoModel
                , editedTodoModel.getTodoId().getId(), headers);
        call.enqueue(new Callback<TodoModel>() {
            @Override
            public void onResponse(Call<TodoModel> call, Response<TodoModel> response) {
                Log.d(TAG, "onResponse: Edited success");
//                EventBus.getDefault().post(new OnReceiveNewTodoEvent(response.body()));
//                EventBus.getDefault().post(new OnAdapterDataSetChangeEvent());
                listener.onResponse(response.body());
            }


            @Override
            public void onFailure(Call<TodoModel> call, Throwable t) {

            }
        });
    }
    public void createTodo(String token, TodoModel todoModel, final ServiceTodoReceivedListener listener) {

        MyRetrofitService service = getRetrofit().create(MyRetrofitService.class);
        HashMap<String, String> headers = new HashMap<>();
        headers.put(HEADER_TOKEN_KEY, token);

//        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Call<TodoModel[]> call=  service.createTodo(todoModel,headers);
        call.enqueue(new Callback<TodoModel[]>() {
            @Override
            public void onResponse(Call<TodoModel[]> call, Response<TodoModel[]> response) {
                Log.d(TAG, "onResponse: success");
//                EventBus.getDefault().post(new OnReceiveNewTodoEvent(response.body()[0]));
                listener.onResponse(response.body()[0]);
            }

            @Override
            public void onFailure(Call<TodoModel[]> call, Throwable t) {

            }
        });
    }


    public class OnAdapterDataSetChangeEvent{

    }

    public interface ServiceTodoReceivedListener {
         void onResponse(TodoModel model);
    }




}
