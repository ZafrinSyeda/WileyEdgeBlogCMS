package com.company.blog.controllers;

import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Post;
import com.company.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * This controller mediates the functions relating to the home page and login pages
 */
@Controller
public class MainController {

    @Autowired
    BlogService service;

    /**
     * sets the home page to display the top 5 most recent posts
     * @param model the home page
     * @return the home page with the relevant information needed to display the posts
     */
    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = service.getRecentApprovedPosts();
        model.addAttribute("posts", posts);
        return "home";
    }

    /**
     * presents the login page
     * @return the login page
     */
   @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * handles when the user logs in, and shows the home page
     * @return
     */
    @PostMapping("login")
    public String postLogin() {
        return "home";
    }

    @GetMapping("logout")
    public String logout() {
        return "home";
    }

}
