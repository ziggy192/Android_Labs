package com.example.nghia.lab2_01;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 11/20/2016.
 */

public class ValueBar extends RelativeLayout {

    @BindView(R.id.iv_add)
    public ImageView ivAdd;

    @BindView(R.id.iv_subtract)
    public ImageView ibSub;

    @BindView(R.id.tv_value)
    public TextView tvValue;

    @BindView(R.id.tv_label)
    public TextView tvLabel;

    private int value  = 0;
    private String label = new String();

    public ValueBar(Context context) {
        super(context);
        initFrom(context,null);
    }

    public ValueBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFrom(context,attrs);

    }

    public ValueBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFrom(context,attrs);

    }

    public void initFrom(Context context, AttributeSet attrs) {
        View viewRoot = inflate(context, R.layout.value_bar, this);
        ButterKnife.bind(this, viewRoot);
        updateUI();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ValueBar);

        value = typedArray.getInt(R.styleable.ValueBar_value,50);
        label = typedArray.getString(R.styleable.ValueBar_label);


        updateUI();

        typedArray.recycle();




    }

    private void updateUI() {
        tvValue.setText(String.format("%s", value));
        tvLabel.setText(label);

    }

    public void addListeners() {
        ivAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                value++;
                updateUI();
            }
        });

        ibSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                value --;
                updateUI();
            }
        });

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateUI();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        updateUI();
    }
}
