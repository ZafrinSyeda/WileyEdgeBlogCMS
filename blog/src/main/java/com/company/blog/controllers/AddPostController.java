package com.company.blog.controllers;

import com.company.blog.dao.HashtagDaoDB;
import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Controller
public class AddPostController {

    @Autowired
    PostDaoDB postDao;

    @Autowired
    HashtagDaoDB hashtagDao;

    @GetMapping("addPost")
    public String addPost() {
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

        String[] hashtagsArr = hashtags.split(",");
        hashtagsArr = hashtags.trim().split("[#,\\s+]+");
        List<Hashtag> hashtagsList = new ArrayList<>();

        for (String hashtagName : hashtagsArr) {
            if (!hashtagName.equals("")) {
                Hashtag ht = new Hashtag();
                ht.setName(hashtagName);
                Hashtag htWithId = hashtagDao.addHashtag(ht);
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

        postDao.addNewPost(post);

        return "redirect:/addPost";
    }

}
