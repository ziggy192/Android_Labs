package com.example.nghia.lab06.view_holder;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.nghia.lab06.R;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.utils.ServiceContext;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nghia on 12/16/2016.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_item_title)
    TextView tvItemTitle;
    @BindView(R.id.tv_item_content)
    TextView tvItemContent;
    @BindView(R.id.ll_container)
    LinearLayout llContainter;
    private TodoModel currentTodoModel;
    private int currentPosition;

    public TodoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }


    public void bind(TodoModel todoModel,int position) {
        this.currentTodoModel = todoModel;
        this.currentPosition = position;
        tvItemTitle.setText(todoModel.getTitle());
        tvItemContent.setText(todoModel.getContent());
        llContainter.setBackgroundColor(Color.parseColor(todoModel.getColor()));

    }

    @Override
    public void onClick(View v) {
//        Log.d("testing", String.format("clicked todoModel = %s", currentTodoModel));
        Log.d("On ItemCLicked",String.format("position = %s", currentPosition));
        EventBus.getDefault().post(new OnItemViewClickedEvent(currentTodoModel,currentPosition));
    }

    public class OnItemViewClickedEvent{
        private TodoModel todoModel;
        private int position;

        public OnItemViewClickedEvent(TodoModel todoModel, int position) {
            this.todoModel = todoModel;
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public TodoModel getTodoModel() {
            return todoModel;
        }
    }

}
