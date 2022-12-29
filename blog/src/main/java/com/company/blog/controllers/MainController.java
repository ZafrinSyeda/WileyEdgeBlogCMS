package com.company.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "home";
    }


   @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String postLogin() {
        return "home";
    }

    @GetMapping("logout")
    public String logout() {
        return "home";
    }


    @GetMapping("addPost")
    public String addPost() {
        return "addPost";
    }

    @GetMapping("approvePost")
    public String approvePost() {
        return "approvePost";
    }
}
