package com.company.blog.dao;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class HashtagDaoDB implements HashtagDao{

    @Autowired
    JdbcTemplate jdbc;

    /**
     * retrieves all the hashtags stored within the database
     *
     * @return every hashtag within the hashtag table
     */
    @Override
    public List<Hashtag> getAllHashtags() {
        final String SELECT_ALL_HASHTAGS = "select * from hashtag";
        List<Hashtag> allHashtags = jdbc.query(SELECT_ALL_HASHTAGS, new HashtagMapper());
        return allHashtags;
    }

    /**
     * retrieves a single hashtag based on some given id
     *
     * @param id of the hashtag to be retrieved
     * @return the hashtag that contains the id being searched for
     */
    @Override
    public Hashtag getHashtagById(int id) {
        try {
            final String SELECT_HASHTAG_BY_ID = "SELECT * FROM hashtag WHERE id = ?";
            return jdbc.queryForObject(SELECT_HASHTAG_BY_ID, new HashtagMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    /**
     * adds a new hashtag entry to the database
     *
     * @param hashtag the hashtag with all the attributes to be added to the database
     * @return the hashtag object being added
     */
    @Override
    @Transactional
    public Hashtag addHashtag(Hashtag hashtag) {
        final String INSERT_HASHTAG = "INSERT INTO hashtag(name) "
                + "VALUES(?)";
        jdbc.update(INSERT_HASHTAG,
                hashtag.getName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hashtag.setId(newId);
        return hashtag;
    }

    /**
     * modifies the hashtag entry
     *
     * @param hashtag with all the necessary modifications to update the entry with
     */
    @Override
    public void editHashtag(Hashtag hashtag) {
        final String UPDATE_HASHTAG = "UPDATE hashtag SET name = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_HASHTAG,
                hashtag.getName(),
                hashtag.getId());
    }

    /**
     * deletes the hashtag entry from the database
     *
     * @param id of the hashtag to be deleted
     */
    @Override
    @Transactional
    public void deleteHashtagById(int id) {
        final String DELETE_POST_HASHTAG = "DELETE FROM post_hashtag WHERE hashtagId = ?";
        jdbc.update(DELETE_POST_HASHTAG, id);

        final String DELETE_HASHTAG = "DELETE FROM hashtag WHERE id = ?";
        jdbc.update(DELETE_HASHTAG, id);


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
