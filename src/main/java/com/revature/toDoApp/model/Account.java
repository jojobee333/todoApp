package com.revature.toDoApp.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Account")
public class Account {
    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + accountId +
                ", account_name='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", todoList=" + todoList +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    @Column(unique = true)
    private String accountName;
    private String password;
    @OneToMany(targetEntity = Todo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todo> todoList = new ArrayList<>();



    public Account(){

    }


    public Account(String accountName, String password){
        this.accountName = accountName;
        this.password = password;

    }

    public Account(int accountId, String accountName, String password){
        this.accountId =  accountId;
        this.accountName = accountName;
        this.password = password;

    }


    public  void addTodo(Todo todo){
        this.todoList.add(todo);
        todo.setAccount(this);

    }

    public void removeTodo(Todo todo){
        this.todoList.remove(todo);
        todo.setAccount(null);
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

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }




}
