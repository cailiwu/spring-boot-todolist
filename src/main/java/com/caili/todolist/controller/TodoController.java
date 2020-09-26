package com.caili.todolist.controller;

import com.caili.todolist.model.entity.Todo;
import com.caili.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity getTodos() {
        Iterable<Todo> todoList = todoService.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @GetMapping("/todos/{id}")
    public Optional<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> todo = todoService.findById(id);
        return todo;
    }

    @PostMapping("/todos")
    public ResponseEntity createTodo(@RequestBody Todo todo) {
        Integer rlt = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity upadteTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        // 呼叫service後，判斷結果，若為true 則回傳ok，反之回傳 400
        Boolean rlt = todoService.updateTodo(id ,todo);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        Boolean rlt = todoService.deleteTodo(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
