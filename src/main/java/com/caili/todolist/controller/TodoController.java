package com.caili.todolist.controller;

import com.caili.todolist.model.entity.Todo;
import com.caili.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.ReactiveStreamsMongoClientDependsOnBeanFactoryPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/todos")
    public String getTodos(Model model) {
        Iterable<Todo> todoList = todoService.getTodo();
        model.addAttribute("todolist", todoList);
        Todo todo = new Todo();
        model.addAttribute("todoObject", todo);
        return "todolist";
    }

    @ResponseBody
    @PostMapping("/todos")
    public String createTodo(@RequestBody Todo todo) {
        // @Responsebody后返回结果不会被解析为跳转路径
       todoService.createTodo(todo);
        return "OK";
    }

    @ResponseBody
    @PutMapping("/todos/{id}")
    public String upadteTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        todoService.updateTodo(id ,todo);
        return "OK";
    }

    @ResponseBody
    @DeleteMapping("/todos/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return "OK";
    }
}
