package com.company.blog.service;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides tests for each of the service methods relating to displaying posts or hashtags
 */
@SpringBootTest
class BlogServiceTest {

    @Autowired
    BlogService service;

    /**
     * tests if all the posts are returned by checking if the number of posts matches what is expected
     */
    @Test
    void getAllPosts() {
        List<Post> posts = service.getAllPosts();
        assertEquals(6, posts.size());
    }

    /**
     * tests whether all the posts in the list returned is set to isApproved
     */
    @Test
    void getApprovedPosts() {
        List<Post> posts = service.getApprovedPosts();
        for (Post post: posts) {
            assertTrue(post.isApproved());
        }
    }

    /**
     * tests both if the most recent posts are approved and if it only displays 5 items
     */
    @Test
    void getRecentApprovedPosts() {
        List<Post> posts = service.getRecentApprovedPosts();
        for (Post post: posts) {
            assertTrue(post.isApproved());
        }
        assertEquals(5, posts.size());
    }

    /**
     * tests whether all the posts in the list returned is not set to isApproved
     */
    @Test
    void getUnapprovedPosts() {
        List<Post> posts = service.getUnapprovedPosts();
        for (Post post: posts) {
            assertFalse(post.isApproved());
        }
    }

    /**
     * tests to see if the correct post is returned by testing whether the post returned is as expected
     */
    @Test
    void getPostById() {
        Post post = service.getPostById(1);
        assertEquals("Hello World", post.getTitle());
    }

    /**
     * tests to see if when using an id that doesn't exist returns null
     */
    @Test
    void getPostByFalseId() {
        Post post = service.getPostById(-1);
        assertNull(post);
    }

    /**
     * tests to see if all the hashtags are returned by ensuring that that the length of the list of
     * hashtags is as intended
     */
    @Test
    void getAllHashtags() {
        List<Hashtag> hashtags = service.getAllHashtags();
        assertEquals(7, hashtags.size());
    }

    /**
     * tests if the right id is returned by checking the attributes of the hashtag
     */
    @Test
    void getHashtagById() {
        Hashtag hashtag = service.getHashtagById(1);
        assertEquals("hello", hashtag.getName());
    }

    /**
     * tests if the right id is returned by checking the attributes of the hashtag
     */
    @Test
    void getHashtagByName() {
        Hashtag hashtag = service.getHashtagByName("hello");
        assertEquals("hello", hashtag.getName());
        assertEquals(1, hashtag.getId());
    }

    /**
     * tests if a hashtag that doesn't exist is returned as null
     */
    @Test
    void getHashtagByFalseId() {
        Hashtag hashtag = service.getHashtagById(-2);
        assertNull(hashtag);
    }
}