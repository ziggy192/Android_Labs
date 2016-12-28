package com.example.nghia.lab06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nghia.lab06.models.Account;
import com.example.nghia.lab06.models.LoginResult;
import com.example.nghia.lab06.models.RegisterResult;
import com.example.nghia.lab06.services.MyRetrofitService;
import com.example.nghia.lab06.utils.ServiceContext;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://a-server.herokuapp.com/api/v2/";
    private static final String TAG = LoginActivity.class.toString();

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        ServiceContext.init(BASE_URL);
    }

    private void accessTodoAccount(String token) {
        Intent intent = new Intent(LoginActivity.this, ToDoActivity.class);
        intent.putExtra(ToDoActivity.TOKEN_KEY, token);
        startActivity(intent);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        Account account  = new Account(etUserName.getText().toString()
                ,etPassword.getText().toString());
        startLogin(account);
    }

    @OnClick(R.id.btn_register)
    public void register() {
        Account account  = new Account(etUserName.getText().toString()
                ,etPassword.getText().toString());
        startRegister(account);
    }

    private void startRegister(Account account) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyRetrofitService service = retrofit.create(MyRetrofitService.class);
        retrofit2.Call<RegisterResult> call = service.startRegister(account);
        call.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                final RegisterResult result = response.body();
                if (result != null) {

                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(result.getMessage());
                        }
                    });
                }
                else{
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("User already exists");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {

            }
        });
    }

    private void startLogin(Account loginAccount) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyRetrofitService service = retrofit.create(MyRetrofitService.class);
        retrofit2.Call<LoginResult> call = service.startLogin(loginAccount);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                final LoginResult result = response.body();
                Log.d(TAG, String.format("onResponse: result = %s", result));
                if (result != null){
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(result.getMessage());
                            accessTodoAccount(result.getToken());
                        }
                    });
                }
                else{
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("User doesn't exist");
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("failed to access server");
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
