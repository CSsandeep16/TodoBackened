package com.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.models.Todo;

public interface TodosRepository extends JpaRepository<Todo, Integer> {
     List<Todo> findByUser(String user);
}
