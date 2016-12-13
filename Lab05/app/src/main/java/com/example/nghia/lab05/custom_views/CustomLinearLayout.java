package com.example.nghia.lab05.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Nghia on 12/3/2016.
 */

public class CustomLinearLayout extends LinearLayout{
    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int height = parenWidth /2;
        int heightMode = MeasureSpec.EXACTLY;
        int newHeightSpec = MeasureSpec.makeMeasureSpec(height, heightMode);
        super.onMeasure(widthMeasureSpec, newHeightSpec);
    }
}
