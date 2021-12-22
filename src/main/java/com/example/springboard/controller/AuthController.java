package com.example.springboard.controller;

import com.example.springboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private String contentPath = "pages/login";
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return contentPath;
    }
}
