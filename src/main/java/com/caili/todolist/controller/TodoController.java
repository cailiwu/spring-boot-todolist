package com.caili.todolist.controller;

import com.caili.todolist.model.entity.Todo;
import com.caili.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/todos")
    public String createTodo(@ModelAttribute Todo todo, Model model) {
        Iterable<Todo> allTodoList = todoService.createTodo(todo);
        Todo emptyTodo = new Todo();
        model.addAttribute("todolist", allTodoList);
        model.addAttribute("todoObject", emptyTodo);
        return "todolist";
    }
}
