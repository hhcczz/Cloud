package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 엔드포인트
    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
    }

    // 로그인 엔드포인트
    @PostMapping("/login")
    public User authenticateUser(@RequestBody User user) {
        return userService.authenticateUser(user.getEmail(), user.getPassword());
    }
}