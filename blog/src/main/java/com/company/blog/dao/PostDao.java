package com.company.blog.dao;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;

import java.util.List;

/**
 * Used to represent all the create, read, update, and delete methods relating to entities within
 * the Post table
 */
public interface PostDao {
    /**
     * Returns all the posts stored within the database
     * @return every entity within the Post table
     */
    List<Post> getAllPosts();

    /**
     * Used to ensure that only approved posts may be displayed
     * @return every post entity where isApproved is true
     */
    List<Post> getApprovedPosts();

    /**
     * returns a single post given some id
     * @param id of the post to be returned
     * @return the post that has the corresponding id
     */
    Post getPostById(int id);

    /**
     * returns a list of posts that all contain the same hashtag
     * @param hashtag the hashtag being searched for
     * @return a list of posts that contain the corresponding hashtag
     */
    List<Post> getPostsByHashtag(Hashtag hashtag);

    /**
     * adds a new Post to the database
     * @param post to be added to the database
     * @return the post that was just added to the database
     */
    Post addNewPost(Post post);

    /**
     * modifies some aspect of the post in the database
     * @param post the post will all the relevant changes
     */
    void editPost(Post post);

    /**
     * removes a post from the database based on some given id
     * @param id of the post to be removed
     */
    void deletePostById(int id);

}