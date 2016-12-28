package com.example.nghia.lab06.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nghia on 12/20/2016.
 */

public class TodoModel implements Serializable{
    public static final String DEFAULT_COLOR = "#FFFF00";
    @SerializedName("content")
    private String content;
    @SerializedName("completed")
    private boolean isCompleted;
    @SerializedName("color")
    private String color;
    @SerializedName("title")
    private String title;
    @SerializedName("_id")
    private TodoId todoId;

    public TodoModel() {
        this.content = "";
        this.isCompleted = false;
        this.color = DEFAULT_COLOR;
        this.title = "";
        this.todoId = new TodoId();
    }

    public TodoModel(String content, boolean isCompleted, String color, String title, TodoId todoId) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.color = color;
        this.title = title;
        this.todoId = todoId;

    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public TodoId getTodoId() {
        return todoId;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", isCompleted=" + isCompleted +
                ", content='" + content + '\'' +
                '}';
    }

    public void setTodoId(TodoId todoId) {
        this.todoId = todoId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
