package com.company.blog.controllers;

import com.company.blog.dao.HashtagDaoDB;
import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Post;
import com.company.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ApprovePostController {
    @Autowired
    BlogService service;

    @GetMapping("approvePost")
    public String approvePost(Model model) {
        List<Post> posts = service.getUnapprovedPosts();
        model.addAttribute("posts", posts);
        return "approvePost";
    }

    @GetMapping("approvedPost")
    public String approvedPost(Integer id) {
        Post post = service.getPostById(id);
        post.setApproved(true);
        post.setTimePosted(LocalDateTime.now());
        service.editPost(post);
        return "redirect:/approvePost";
    }

    @GetMapping("disapprovePost")
    public String disapprovePost(Integer id) {
        service.deletePostById(id);
        return "redirect:/approvePost";
    }
}
