package com.example.nghia.lab05.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.nghia.lab05.R;
import com.example.nghia.lab05.models.ResponseItem;

import java.util.List;

/**
 * Created by Nghia on 12/11/2016.
 */

public class ResponseItemAdapter extends ArrayAdapter<ResponseItem> {
    public ResponseItemAdapter(Context context, int resource, List<ResponseItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.response_item_list_view, parent, false);
        }
        new ItemViewHolder(convertView).setData(getContext(),getItem(position));
        return convertView;
    }
}
