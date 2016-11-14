package com.example.nghia.lab_0102;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Detail_Activity extends AppCompatActivity {
    Button btn_addToCart_small;
    Button btn_addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        btn_addToCart_small = (Button) this.findViewById(R.id.btn_add_to_cart_small);
        btn_addToCart = (Button) this.findViewById(R.id.btn_add_to_cart);
        btn_addToCart_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Activity.this, OptionActivity.class);
                startActivity(intent);
            }
        });

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Activity.this, OptionActivity.class);
                startActivity(intent);
            }
        });

    }
}
