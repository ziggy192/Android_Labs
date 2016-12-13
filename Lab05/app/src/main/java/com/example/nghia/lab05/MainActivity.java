package com.example.nghia.lab05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.nghia.lab05.custom_views.ResponseItemAdapter;
import com.example.nghia.lab05.models.ResponseItem;
import com.example.nghia.lab05.models.ThirtyShineRequestBody;
import com.example.nghia.lab05.models.ThirtyShineResponseBody;
import com.example.nghia.lab05.service.ThirtyShineService;
import com.example.nghia.lab05.utils.DbContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://api.30shine.com/category/";
    private static final String TAG = MainActivity.class.toString();
    @BindView(R.id.lv_response_items)
    ListView lvResponseItem;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DbContext.init(this);
        startGetingData();
//        testRealm();
    }

    private void insertOrUpdateResponseBodyToRealm(ThirtyShineResponseBody body) {
        ThirtyShineResponseBody oldBody = DbContext.getInstance().selectBody();
        if (oldBody != null) {
            DbContext.getInstance().deleteObjectFromRealm(oldBody);
        }
        DbContext.getInstance().insertResponseBody(body);
    }

    private void testRealm() {
        Log.d(TAG, "testRealm:");
        ThirtyShineResponseBody body = DbContext.getInstance().selectBody();

        for (ResponseItem item : body.getResponseItems()) {
            Log.d(TAG, String.format("onResponse: %s", item));
        }
        insertOrUpdateResponseBodyToRealm(body);
        testIfUpdateBodySuccess();

//        DbContext.getInstance().deleteObjectFromRealm(body);
//        body = DbContext.getInstance().selectBody();
//        if (body == null) {
//            Log.d(TAG, "delete successfull");
//        } else {
//            Log.d(TAG, "delete FAILE");
//        }
    }

    private void testIfUpdateBodySuccess() {
        Log.d(TAG, String.format("onTesting: Total bodies =  %s", DbContext.getInstance().getBodiesSize()));
    }


    private void startGetingData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ThirtyShineService service = retrofit.create(ThirtyShineService.class);
        Call<ThirtyShineResponseBody> call = service.postToGetData(new ThirtyShineRequestBody());
        call.enqueue(new Callback<ThirtyShineResponseBody>() {
            @Override
            public void onResponse(Call<ThirtyShineResponseBody> call, Response<ThirtyShineResponseBody> response) {
                ThirtyShineResponseBody body = response.body();
                for (ResponseItem item : body.getResponseItems()) {

                    Log.d(TAG, String.format("onResponse: %s", item));
                }
//                DbContext.getInstance().copyOrUpdateResponseBody(body);
//                testRealm();
                insertOrUpdateResponseBodyToRealm(body);
                testIfUpdateBodySuccess();

                setupUI(body.getResponseItems());


            }

            @Override
            public void onFailure(Call<ThirtyShineResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure, get old body !!!");
//                testRealm();
                ThirtyShineResponseBody body = getOldResponseBody();
                for (ResponseItem item : body.getResponseItems()) {
                    Log.d(TAG, String.format("%s", item));
                }
                setupUI(body.getResponseItems());


            }
        });
    }

    private void setupUI(final List<ResponseItem> responseItemList) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lvResponseItem.setAdapter(
                        new ResponseItemAdapter(MainActivity.this
                        ,R.layout.response_item_list_view
                        ,responseItemList)
                );
                pbLoading.setVisibility(View.GONE);
                lvResponseItem.setVisibility(View.VISIBLE);
            }
        });
    }

    public ThirtyShineResponseBody getOldResponseBody() {
        return DbContext.getInstance().selectBody();
    }
}
