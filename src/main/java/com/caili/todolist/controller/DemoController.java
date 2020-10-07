package com.caili.todolist.controller;

import com.caili.todolist.model.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {
    @GetMapping("/admin")
    @ResponseBody
    public String sayAdminHi() {
        return "Hi ADMIN";
    }

    @GetMapping("/user")
    @ResponseBody
    public String sayUserHi() {
        return "Hi USER";
    }
}
