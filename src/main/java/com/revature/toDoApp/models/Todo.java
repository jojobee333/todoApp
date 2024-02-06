package com.revature.toDoApp.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int todoId;
    private String text;

    // linked to account
    private int createdBy;



    private boolean completed;

    public Todo(String text){
        this.text = text;
        this.completed = false;
    }
    public Todo(String text, boolean completed){
        this.text = text;
        this.completed = completed;

    }

    public Todo(int todoId, String text, boolean completed){
        this.todoId = todoId;
        this.text = text;
        this.completed = completed;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {

        this.todoId = todoId;
    }

    public int getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }











}
