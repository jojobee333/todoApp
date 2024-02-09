package com.revature.toDoApp.repository;
import com.revature.toDoApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {



    @Query("FROM Account a WHERE a.name = :name")
    Optional<Account> findByName(@Param("name") String name);





}