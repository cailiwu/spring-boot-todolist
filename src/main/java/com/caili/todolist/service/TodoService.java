package com.caili.todolist.service;

import com.caili.todolist.model.dao.TodoDao;
import com.caili.todolist.model.entity.Todo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoDao todoDao;

    public Iterable<Todo> getTodos() {
        return todoDao.findAll();
    }

    public Integer createTodo(Todo todo) {
        Todo rltTodo = todoDao.save(todo);
        return rltTodo.getId();
    }

    public Boolean updateTodo(Integer id,Todo todo) {
            Optional<Todo> isExistTodo = findById(id);
            if (! isExistTodo.isPresent()) {
                return false;
            }
            Todo newTodo = isExistTodo.get();
            if (todo.getStatus() == null) {
                return false;
            }
            newTodo.setStatus(todo.getStatus());
            todoDao.save(newTodo);
            return true;
    }

    public Optional<Todo> findById(Integer id) {
        Optional<Todo> todo = todoDao.findById(id);
        return todo;
    }

    public Boolean deleteTodo(Integer id) {
            Optional<Todo> findTodo = findById(id);
            if (!findTodo.isPresent()) {
                return false;
            }
            todoDao.deleteById(id);
            return true;
    }
}
