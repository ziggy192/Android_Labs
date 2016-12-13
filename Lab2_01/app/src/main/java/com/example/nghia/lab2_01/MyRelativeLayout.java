package com.example.nghia.lab2_01;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Nghia on 11/20/2016.
 */

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int widthSize = parentWidth / 2;
        int heightSize = parentHeight;

        int widthMode  = MeasureSpec.EXACTLY;
        int heightMode = MeasureSpec.EXACTLY;

        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
    }
}
