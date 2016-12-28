package com.example.nghia.lab06.events;

import com.example.nghia.lab06.models.TodoModel;

/**
 * Created by Nghia on 12/25/2016.
 */

public class OnReceiveNewTodoEvent {
    TodoModel todoModel;
    TodoModel oldTodoModel;
    Action action;
    int adapterPosition;


    public OnReceiveNewTodoEvent(TodoModel todoModel, TodoModel oldTodoModel,Action action, int adapterPosition) {
        //used to created editing model event
        this.todoModel = todoModel;
        this.action = action;
        this.adapterPosition = adapterPosition;
        this.oldTodoModel = oldTodoModel;
    }

    public OnReceiveNewTodoEvent(TodoModel todoModel, Action action) {
        //used to create adding model event
        this.todoModel = todoModel;
        this.action = action;
        this.adapterPosition = -1;
    }

    public TodoModel getTodoModel() {
        return todoModel;
    }

    public Action getAction() {
        return action;
    }

    public TodoModel getOldTodoModel() {
        return oldTodoModel;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    public enum Action {
        ADD,
        EDIT
    }

}
