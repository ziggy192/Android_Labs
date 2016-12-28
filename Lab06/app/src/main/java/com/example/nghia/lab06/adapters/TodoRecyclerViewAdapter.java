package com.example.nghia.lab06.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nghia.lab06.R;
import com.example.nghia.lab06.models.TodoModel;
import com.example.nghia.lab06.view_holder.TodoViewHolder;

import java.util.List;

/**
 * Created by Nghia on 12/16/2016.
 */

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private List<TodoModel> noteList;

    public TodoRecyclerViewAdapter(List<TodoModel> noteList) {
        this.noteList = noteList;
    }

    public List<TodoModel> getNoteList() {
        return noteList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_todo_view, parent, false);
        TodoViewHolder holder  = new TodoViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.bind(noteList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


}
