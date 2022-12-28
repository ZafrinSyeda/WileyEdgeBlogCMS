package com.company.blog.dao;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class HashtagDaoDB implements HashtagDao{
    /**
     * retrieves all the hashtags stored within the database
     *
     * @return every hashtag within the hashtag table
     */
    @Override
    public List<Hashtag> getAllHashtags() {
        return null;
    }

    /**
     * retrieves a single hashtag based on some given id
     *
     * @param id of the hashtag to be retrieved
     * @return the hashtag that contains the id being searched for
     */
    @Override
    public Hashtag getHashtagById(int id) {
        return null;
    }

    /**
     * adds a new hashtag entry to the database
     *
     * @param hashtag the hashtag with all the attributes to be added to the database
     * @return the hashtag object being added
     */
    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
        return null;
    }

    /**
     * modifies the hashtag entry
     *
     * @param hashtag with all the necessary modifications to update the entry with
     */
    @Override
    public void editHashtag(Hashtag hashtag) {

    }

    /**
     * deletes the hashtag entry from the database
     *
     * @param id of the hashtag to be deleted
     */
    @Override
    public void deleteHashtagById(int id) {

    }

    public static final class HashtagMapper implements RowMapper<Hashtag> {
        @Override
        public Hashtag mapRow(ResultSet rs, int index) throws SQLException {
            Hashtag ht = new Hashtag();
            ht.setId(rs.getInt("id"));
            ht.setName(rs.getString("name"));
            return ht;
        }
    }
}
