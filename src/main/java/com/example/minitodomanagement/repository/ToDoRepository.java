package com.example.minitodomanagement.repository;

import com.example.minitodomanagement.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUsername(String user);


}
