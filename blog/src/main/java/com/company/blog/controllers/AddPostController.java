package com.company.blog.controllers;

import com.company.blog.dao.HashtagDaoDB;
import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import com.company.blog.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AddPostController {

    @Autowired
    BlogService service;

    Set<ConstraintViolation<Post>> postErrors = new HashSet<>();

    @GetMapping("addPost")
    public String addPost(Model model) {
        model.addAttribute("postErrors", postErrors);
        return "addPost";
    }

    @PostMapping("addPost")
    public String addPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String blogBody = request.getParameter("blogBody");
        String hashtags = request.getParameter("hashtags");

        Post post = new Post();
        post.setTitle(title);
        post.setBlogBody(blogBody);

        String[] hashtagsArr = hashtags.trim().split("[#,\\s+]+");
        List<Hashtag> hashtagsList = new ArrayList<>();

        for (String hashtagName : hashtagsArr) {
            if (!hashtagName.equals("")) {
                Hashtag ht = new Hashtag();
                ht.setName(hashtagName);
                Hashtag htWithId = service.addHashtag(ht);
                hashtagsList.add(htWithId);
            }
        }
        post.setHashtags(hashtagsList);
        post.setTimePosted(LocalDateTime.now());
        if (request.isUserInRole("ADMIN")) {
            post.setApproved(true);
        } else {
            post.setApproved(false);
        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        postErrors = validate.validate(post);
        if(postErrors.isEmpty()) {
            service.addNewPost(post);
        }

        return "redirect:/addPost";
    }

}
