package com.company.blog.dao;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoDB implements PostDao {

    @Autowired
    JdbcTemplate jdbc;

    /**
     * Returns all the posts stored within the database
     *
     * @return every entity within the Post table
     */
    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    /**
     * Used to ensure that only approved posts may be displayed
     *
     * @return every post entity where isApproved is true
     */
    @Override
    public List<Post> getApprovedPosts() {
        return null;
    }

    /**
     * returns a single post given some id
     *
     * @param id of the post to be returned
     * @return the post that has the corresponding id
     */
    @Override
    public Post getPostById(int id) {
        return null;
    }

    /**
     * returns a list of posts that all contain the same hashtag
     *
     * @param hashtag the hashtag being searched for
     * @return a list of posts that contain the corresponding hashtag
     */
    @Override
    public List<Post> getPostsByHashtag(Hashtag hashtag) {
        return null;
    }

    /**
     * adds a new Post to the database
     *
     * @param post to be added to the database
     * @return the post that was just added to the database
     */
    @Override
    public Post addNewPost(Post post) {
        return null;
    }

    /**
     * modifies some aspect of the post in the database
     *
     * @param post the post will all the relevant changes
     */
    @Override
    public void editPost(Post post) {

    }

    /**
     * removes a post from the database based on some given id
     *
     * @param id of the post to be removed
     */
    @Override
    public void deletePostById(int id) {

    }
}
