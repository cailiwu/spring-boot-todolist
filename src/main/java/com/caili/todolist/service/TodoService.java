package com.caili.todolist.service;

import com.caili.todolist.model.dao.TodoDao;
import com.caili.todolist.model.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class TodoService {
    @Autowired
    TodoDao todoDao;

    public Iterable<Todo> getTodo() {
        return todoDao.findAll();
    }

    public Iterable<Todo> createTodo(Todo todo) {
        todoDao.save(todo);
        return getTodo();
    }

    public Todo updateTodo(Integer id,Todo todo) {
        try {
            Todo resTodo = findById(id);
            Integer status = todo.getStatus();
            resTodo.setStatus(status);
            return todoDao.save(resTodo);
        }catch (Exception exception) {
            return null;
        }

    }

    public Todo findById(Integer id) {
        Todo todo = todoDao.findById(id).get();
        return todo;
    }

    public Boolean deleteTodo(Integer id) {
        try {
            Todo resTodo = findById(id);
            todoDao.deleteById(id);
            return true;
        }catch (Exception exception) {
            return false;
        }
    }
}
