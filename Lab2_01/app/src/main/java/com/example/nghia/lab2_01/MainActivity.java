package com.example.nghia.lab2_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.value_bar)
    public ValueBar valueBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ButterKnife.bind(this);

        valueBar = (ValueBar) findViewById(R.id.value_bar);

        valueBar.addListeners();



    }
}
