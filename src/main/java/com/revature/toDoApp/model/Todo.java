package com.revature.toDoApp.model;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Todo")
public class Todo {


    @Column(name="todo_id")
    private @Id @GeneratedValue(strategy= GenerationType.AUTO) int todo_id;


    @Column(name="text")
    private String text;

    @Column(name="account_name")
    private String account_name;

    @Column(name="completed")
    private Boolean completed;

    public Todo(){

    }


    public Todo(String text, Boolean completed, String accountName){
        this.text = text;
        this.completed = completed;
        this.account_name = accountName;
    }
    public Todo(String text, Boolean completed, int todoId, String accountName){
        this.todo_id = todoId;
        this.text = text;
        this.completed = completed;
        this.account_name = accountName;
    }

    public int getTodo_id() {
        return todo_id;

    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String toString() {
        return "Todo{" + "id= " + this.todo_id + '\'' + ", text= '" + this.text + '\'' + ", isCompleted= '" + completed + '\'' + "createdBy= '" + account_name + '\'' +  '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo todo)) return false;
        return todo_id == todo.todo_id && completed == todo.completed && Objects.equals(text, todo.text) && Objects.equals(account_name, todo.account_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todo_id, text, account_name, completed);
    }
}
