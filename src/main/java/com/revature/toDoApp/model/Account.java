package com.revature.toDoApp.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Account")
public class Account {

    @Column(name="account_id")
    private @Id @GeneratedValue(strategy= GenerationType.AUTO) int account_id;

    @Column(name="name")
    private String name;


    @Column(name="password")
    private String password;


    @Column(name="is_admin")
    private boolean is_admin;

    public Account(){

    }
    public Account(String name, String password, boolean isAdmin){
        this.name = name;
        this.password = password;
        this.is_admin = isAdmin;
    }

    public Account(int accountId, String name, String password, boolean isAdmin){
        this.account_id =  accountId;
        this.name = name;
        this.password = password;
        this.is_admin = isAdmin;
    }




    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return account_id == account.account_id && is_admin == account.is_admin && Objects.equals(name, account.name) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_id, name, password, is_admin);
    }
}
