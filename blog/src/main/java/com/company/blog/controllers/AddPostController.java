package com.company.blog.controllers;

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

/**
 * This controller mediates the functions relating to adding a new post to allow the user to add posts to the blog
 */
@Controller
public class AddPostController {

    @Autowired
    BlogService service;

    Set<ConstraintViolation<Post>> postErrors = new HashSet<>();

    /**
     * Presents the user with the 'add post' page
     * @param model the webpage
     * @return the page
     */
    @GetMapping("addPost")
    public String addPost(Model model) {
        /*
        sets up the possible errors that the user can face when uploading a post
         */
        model.addAttribute("postErrors", postErrors);
        return "addPost";
    }

    /**
     * Takes in the user input to create a new post or not after validating it
     * @param request to take the parameters in the add new post form
     * @return the addpost page
     */
    @PostMapping("addPost")
    public String addPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String blogBody = request.getParameter("blogBody");
        String hashtags = request.getParameter("hashtags");

        Post post = new Post();
        post.setTitle(title);
        post.setBlogBody(blogBody);

        /*
         Hashtags can either be split by # or , or space, so the regex is used to consider all 3 of these
         when splitting the string to generate the hashtags
         */
        String[] hashtagsArr = hashtags.trim().split("[#,\\s+]+");
        List<Hashtag> hashtagsList = new ArrayList<>();
        /*
        goes through each of the hashtags and adds them to the hashtag list
         */
        for (String hashtagName : hashtagsArr) {
            /*
            the first if statement accounts for the split function created an empty string
             */
            if (!hashtagName.equals("")) {
                Hashtag ht = new Hashtag();
                ht.setName(hashtagName);
                Hashtag htWithId = service.addHashtag(ht);
                hashtagsList.add(htWithId);
            }
        }
        post.setHashtags(hashtagsList);
        post.setTimePosted(LocalDateTime.now());
        /*
        if the user is the admin the post is immediately approved and gets uploaded immediately on site
        but if they're not it will not be approved
         */
        if (request.isUserInRole("ADMIN")) {
            post.setApproved(true);
        } else {
            post.setApproved(false);
        }
        /*
        checks for any validation errors and if there isn't the post will be added
         */
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        postErrors = validate.validate(post);
        if(postErrors.isEmpty()) {
            service.addNewPost(post);
        }

        return "redirect:/addPost";
    }

}
