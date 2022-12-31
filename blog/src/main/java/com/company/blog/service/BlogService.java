package com.company.blog.service;

import com.company.blog.dao.HashtagDao;
import com.company.blog.dao.PostDaoDB;
import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BlogService {
    @Autowired
    PostDaoDB postDao;

    @Autowired
    HashtagDao hashtagDao;

    /**
     * Returns all the posts stored within the database
     * @return every entity within the Post table
     */
    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }

    /**
     * Used to ensure that only approved posts may be displayed
     * @return every post entity where isApproved is true
     */
    public List<Post> getApprovedPosts() {
        return postDao.getApprovedPosts();
    }

    /**
     * Used to ensure that only the most recent x approved posts may be displayed
     * @return some number of approved posts
     */
    public List<Post> getRecentApprovedPosts(){
        return postDao.getRecentApprovedPosts();
    }

    /**
     * Used to only display unapproved posts
     * @return every post entity where isApproved is false
     */
    public List<Post> getUnapprovedPosts() {
        return postDao.getUnapprovedPosts();
    }

    /**
     * returns a single post given some id
     * @param id of the post to be returned
     * @return the post that has the corresponding id
     */
    public Post getPostById(int id) {
        return postDao.getPostById(id);
    }

    /**
     * returns a list of posts that all contain the same hashtag
     * @param hashtag the hashtag being searched for
     * @return a list of posts that contain the corresponding hashtag
     */
    public List<Post> getPostsByHashtag(Hashtag hashtag) {
        return postDao.getPostsByHashtag(hashtag);
    }

    /**
     * adds a new Post to the database
     * @param post to be added to the database
     * @return the post that was just added to the database
     */
    public Post addNewPost(Post post) {
        return postDao.addNewPost(post);
    }

    /**
     * modifies some aspect of the post in the database
     * @param post the post will all the relevant changes
     */
    public void editPost(Post post) {
        postDao.editPost(post);
    }

    /**
     * removes a post from the database based on some given id
     * @param id of the post to be removed
     */
    public void deletePostById(int id) {
        postDao.deletePostById(id);
    }

    /**
     * retrieves all the hashtags stored within the database
     * @return every hashtag within the hashtag table
     */
    public List<Hashtag> getAllHashtags() {
        return hashtagDao.getAllHashtags();
    }

    /**
     * retrieves a single hashtag based on some given id
     * @param id of the hashtag to be retrieved
     * @return the hashtag that contains the id being searched for
     */
    public Hashtag getHashtagById(int id) {
        return hashtagDao.getHashtagById(id);
    }

    /**
     * adds a new hashtag entry to the database
     * @param hashtag the hashtag with all the attributes to be added to the database
     * @return the hashtag object being added
     */
    public Hashtag addHashtag(Hashtag hashtag) {
        return hashtagDao.addHashtag(hashtag);
    }

    /**
     * modifies the hashtag entry
     * @param hashtag with all the necessary modifications to update the entry with
     */
    public void editHashtag(Hashtag hashtag) {
        hashtagDao.editHashtag(hashtag);
    }

    /**
     * deletes the hashtag entry from the database
     * @param id of the hashtag to be deleted
     */
    public void deleteHashtagById(int id) {
        hashtagDao.deleteHashtagById(id);
    }
}
