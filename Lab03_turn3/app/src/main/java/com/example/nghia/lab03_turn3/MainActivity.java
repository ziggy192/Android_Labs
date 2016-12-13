package com.example.nghia.lab03_turn3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nghia.lab03_turn3.models.Salon;
import com.example.nghia.lab03_turn3.models.SalonGroup;
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

        private ListView listView;
    private ArrayAdapter<Salon> salonArrayAdapter;
    private SalonGroup salonGroup;
    String url = "http://a-server.herokuapp.com/api/hairstyle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        getReferences();
        configUI();

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

                 salonGroup = gson.fromJson(body, SalonGroup.class);


                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update views
                        salonArrayAdapter.notifyDataSetChanged();
                    }
                });

                        Log.d(TAG, String.format("%s", salonGroup));





            }
        });
    }

    private void initData() {
        salonGroup = new SalonGroup(new ArrayList<Salon>());
    }

    public void getReferences() {
        listView = (ListView) findViewById(R.id.lv_post);

    }

    private void configUI() {
        salonArrayAdapter = new ArrayAdapter<Salon>(this, android.R.layout.simple_list_item_1,salonGroup.getSalons() );
        listView.setAdapter(salonArrayAdapter);

    }
}
