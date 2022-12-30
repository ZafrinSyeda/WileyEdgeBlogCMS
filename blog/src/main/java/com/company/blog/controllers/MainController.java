package com.company.blog.controllers;

import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostDaoDB postDao;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postDao.getRecentApprovedPosts();
        model.addAttribute("posts", posts);

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




    @GetMapping("approvePost")
    public String approvePost() {
        return "approvePost";
    }
}
