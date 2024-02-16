package com.revature.toDoApp.dto;

public class TodoDTO {
    private int todoId;
    private String text;
    private String accountName;
    private boolean completed;


    public TodoDTO(String text, boolean completed, String accountName) {
        this.text = text;
        this.completed = completed;
        this.accountName = accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        return "TodoDTO{" +
                "todoId=" + todoId +
                ", text='" + text + '\'' +
                ", accountName='" + accountName + '\'' +
                ", completed=" + completed +
                '}';
    }
}
