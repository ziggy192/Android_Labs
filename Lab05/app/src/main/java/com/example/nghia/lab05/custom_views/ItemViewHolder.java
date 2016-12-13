package com.example.nghia.lab05.custom_views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghia.lab05.R;
import com.example.nghia.lab05.models.ResponseItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/3/2016.
 */

public class ItemViewHolder {

    @BindView(R.id.imv_item_photo)
    ImageView imvItemPhoto;
    @BindView(R.id.tv_item_title)
    TextView tvItemTitle;


    public ItemViewHolder(View rootView) {
        ButterKnife.bind(this, rootView);
    }
    public void setData(Context context, ResponseItem responseItem) {
        tvItemTitle.setText(responseItem.getName());
//        tvFoodName.setText(foodItem.getName());
//        tvFoodDetail.setText(foodItem.getDetail());
//        tvFoodPrice.setText(foodItem.getPriceText());
//        Picasso.with(context).load(foodItem.getImageUrl()).into(ivFoodImage);
        Picasso.with(context).load(responseItem.getImage().getUrl()).into(imvItemPhoto);

    }
}
