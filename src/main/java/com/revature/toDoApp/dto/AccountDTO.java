package com.revature.toDoApp.dto;

import java.util.List;

public class AccountDTO {
    private int accountId;
    private String accountName;
    private String password; // Consider excluding sensitive data like passwords in DTOs
    private List<TodoDTO> todoList;

    public AccountDTO(){

    }

    public AccountDTO(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public AccountDTO(String accountName,  int accountId) {
        this.accountName = accountName;
        this.accountId = accountId;
    }

    public AccountDTO(int accountId, String accountName, String password, List<TodoDTO> todoList) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
        this.todoList = todoList;
    }


    // Getters and Setters

    public void addTodoList(TodoDTO todo) {
        this.todoList.add(todo);
    }

    public void removeTodoList(TodoDTO todo){
        this.todoList.remove(todo);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TodoDTO> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoDTO> todoList) {
        this.todoList = todoList;
    }
}
