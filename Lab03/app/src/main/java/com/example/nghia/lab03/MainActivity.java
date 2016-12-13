package com.example.nghia.lab03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nghia.lab03.jsonModels.SalonGroup;
import com.example.nghia.lab03.jsonModels.FatherOfD;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.toString();

//    private ListView listView;
//    private ArrayAdapter<FatherOfD> salonArrayAdapter;
//    private ArrayList<FatherOfD> salonArrayList;
    String url = "http://a-server.herokuapp.com/api/salon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        getReferences();
//        configUI();

        sendGET();

    }



    private void sendGET() {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, String.format("onFailure %s", e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();

                Log.d(TAG, String.format("onResponse: %s", body));

                Gson gson = new Gson();

                SalonGroup salons = gson.fromJson(body, SalonGroup.class);

//                salonArrayList.clear();
//                salonArrayList.addAll(Arrays.asList(salons));

//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Update views
//                        salonArrayAdapter.notifyDataSetChanged();
//                    }
//                });

                Log.d(TAG, String.format("%s", salons));





            }
        });
    }

    private void initData() {
//        salonArrayList = new ArrayList<>();
    }

    public void getReferences() {
//        listView = (ListView) findViewById(R.id.lv_post);

    }

//    private void configUI() {
//        salonArrayAdapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1, );
//        listView.setAdapter(salonArrayAdapter);
//
//    }
}
