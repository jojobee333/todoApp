package com.revature.toDoApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="Todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todo_id;

    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private boolean completed;

    public Todo() {
    }

    public Todo(String text, Boolean completed, Account account) {
        this.text = text;
        this.completed = completed;
        this.account = account;
    }

    // Getters and Setters

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // toString, equals, and hashCode methods
}
