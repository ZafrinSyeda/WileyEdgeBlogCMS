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

/**
 * This controller mediates the functions relating to approving posts by the admin
 */
@Controller
public class ApprovePostController {
    @Autowired
    BlogService service;

    /**
     * presents all the unapproved posts to be displayed on this page
     * @param model the webpage
     * @return the approvepost page showing any unapproved posts
     */
    @GetMapping("approvePost")
    public String approvePost(Model model) {
        List<Post> posts = service.getUnapprovedPosts();
        model.addAttribute("posts", posts);
        return "approvePost";
    }

    /**
     * handles when a post is set to be approved
     * @param id of the post being approved
     * @return the approvepost page which will no longer display that post
     */
    @GetMapping("approvedPost")
    public String approvedPost(Integer id) {
        Post post = service.getPostById(id);
        post.setApproved(true);
        post.setTimePosted(LocalDateTime.now());
        service.editPost(post);
        return "redirect:/approvePost";
    }

    /**
     * handles when a post is set to be disapproved by deleting it from the database entirely
     * @param id of the post being disapproved
     * @return the approved post page
     */
    @GetMapping("disapprovePost")
    public String disapprovePost(Integer id) {
        service.deletePostById(id);
        return "redirect:/approvePost";
    }
}
