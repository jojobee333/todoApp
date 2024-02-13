package com.revature.toDoApp.repository;

import com.revature.toDoApp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("FROM Todo t WHERE t.text = :text")
    Optional<Todo> findByText(@Param("text") String text);

    @Query("FROM Todo t WHERE t.account.accountId = :accountId")
    List<Todo> findByAccountId(@Param("accountId") Integer accountId);

    @Query("FROM Todo t WHERE t.completed = :completed")
    List<Todo> findByCompleted(@Param("completed") boolean completed);
}
