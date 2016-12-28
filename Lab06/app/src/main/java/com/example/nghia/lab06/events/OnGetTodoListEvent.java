package com.example.nghia.lab06.events;

import com.example.nghia.lab06.models.TodoModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Nghia on 12/24/2016.
 */

public class OnGetTodoListEvent {
    ArrayList<TodoModel> todoModels;

    public OnGetTodoListEvent(ArrayList<TodoModel> todoModels) {
        this.todoModels = todoModels;
    }

    public ArrayList<TodoModel> getTodoModels() {
        return todoModels;
    }
}
