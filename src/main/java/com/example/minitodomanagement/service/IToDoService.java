package com.example.minitodomanagement.service;

import com.example.minitodomanagement.model.Todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IToDoService {
    List< Todo > getTodosByUser(String user);

    Optional< Todo > getTodoById(long id);

    void updateTodo(Todo todo);

    void addTodo(String name, String desc, Date targetDate, boolean isDone);

    void deleteTodo(long id);

    void saveTodo(Todo todo);
}
