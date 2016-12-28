package com.example.nghia.lab06.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.nghia.lab06.R;
import com.example.nghia.lab06.models.ColorChoosingItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/24/2016.
 */

public class ColorChoosingSpinnerAdapter extends ArrayAdapter<ColorChoosingItem> {
    @BindView(R.id.imv_color_item)
    ImageView imvColorItem;
    @BindView(R.id.tv_color_item)
    TextView tvColorItem;
    LayoutInflater inflater;
    public ColorChoosingSpinnerAdapter(Context context, int resource, ColorChoosingItem[] objects) {
        super(context, resource,R.id.tv_item_title ,objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.color_choose_spinner_item, parent,false);
        }
        ButterKnife.bind(this, convertView);
        ColorChoosingItem item = getItem(position);
        imvColorItem.setBackgroundColor(Color.parseColor(item.getColorString()));
        tvColorItem.setText(item.getColorName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
