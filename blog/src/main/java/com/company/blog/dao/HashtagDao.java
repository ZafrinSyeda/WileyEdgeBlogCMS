package com.company.blog.dao;

import com.company.blog.entities.Hashtag;

import java.util.List;

/**
 * Used to represent all the create, read, update, and delete methods relating to entities within
 * the Hashtag table
 */
public interface HashtagDao {
    /**
     * retrieves all the hashtags stored within the database
     * @return every hashtag within the hashtag table
     */
    List<Hashtag> getAllHashtags();

    /**
     * retrieves a single hashtag based on some given id
     * @param id of the hashtag to be retrieved
     * @return the hashtag that contains the id being searched for
     */
    Hashtag getHashtagById(int id);

    /**
     * adds a new hashtag entry to the database
     * @param hashtag the hashtag with all the attributes to be added to the database
     * @return the hashtag object being added
     */
    Hashtag addHashtag(Hashtag hashtag);

    /**
     * modifies the hashtag entry
     * @param hashtag with all the necessary modifications to update the entry with
     */
    void editHashtag(Hashtag hashtag);

    /**
     * deletes the hashtag entry from the database
     * @param id of the hashtag to be deleted
     */
    void deleteHashtagById(int id);
}
