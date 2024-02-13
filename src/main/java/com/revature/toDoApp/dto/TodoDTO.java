package com.revature.toDoApp.dto;

public class TodoDTO {
    private int todoId;
    private String text;
    private int accountId;
    private boolean completed;


    public TodoDTO(String text, Boolean completed, int accountId) {
        this.text = text;
        this.completed = completed;
        this.accountId = accountId;
    }

    public TodoDTO() {

    }


    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }




}
