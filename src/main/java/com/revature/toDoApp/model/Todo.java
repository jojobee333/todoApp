package com.revature.toDoApp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todo_id;

    private String text;

    @ManyToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo todo)) return false;
        return todo_id == todo.todo_id && completed == todo.completed && Objects.equals(text, todo.text) && Objects.equals(account, todo.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todo_id, text, account, completed);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todo_id +
                ", text='" + text + '\'' +
                ", account=" + account +
                ", completed=" + completed +
                '}';
    }
}
