package com.company.blog.dao;

import com.company.blog.entities.Hashtag;
import com.company.blog.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        final String SELECT_ALL_POSTS = "select * from post";
        List<Post> allPosts = jdbc.query(SELECT_ALL_POSTS, new PostMapper());
        addHashtagsToList(allPosts);
        return allPosts;
    }

    /**
     * Used to ensure that only approved posts may be displayed
     *
     * @return every post entity where isApproved is true
     */
    @Override
    public List<Post> getApprovedPosts() {
        final String SELECT_ALL_APPROVED_POSTS = "select * from post where isApproved = true";
        List<Post> allApprovedPosts = jdbc.query(SELECT_ALL_APPROVED_POSTS, new PostMapper());
        addHashtagsToList(allApprovedPosts);
        return allApprovedPosts;
    }

    /**
     * returns a single post given some id
     *
     * @param id of the post to be returned
     * @return the post that has the corresponding id
     */
    @Override
    public Post getPostById(int id) {
        try {
            final String SELECT_POST_BY_ID = "select * from post where id = ?";
            Post post =  jdbc.queryForObject(SELECT_POST_BY_ID,
                    new PostMapper(), id);
            /**
             * needs to call on separate methods to return organisation and location objects
             */
            post.setHashtags(getPostHashtags(id));
            return post;
        } catch (DataAccessException e) {
            // reaches here if the user tries to access an id of a post that does not exist
            return null;
        }
    }

    private List<Hashtag> getPostHashtags(int id) {
        try {
            /**
             * uses a join clause between the post table and the bridge table
             * between the hashtag table
             */
            final String SELECT_HASHTAGS_FOR_POST = "SELECT h.* \n" +
                    "FROM hashtag h \n" +
                    "JOIN post_hashtag ph \n" +
                    "ON ph.hashtagId = h.id \n" +
                    "WHERE ph.postId = ?;";
            return jdbc.query(SELECT_HASHTAGS_FOR_POST, new HashtagDaoDB.HashtagMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    private void addHashtagsToList(List<Post> posts) {
        for (Post p : posts) {
            p.setHashtags(getPostHashtags(p.getId()));
        }
    }

    /**
     * returns a list of posts that all contain the same hashtag
     *
     * @param hashtag the hashtag being searched for
     * @return a list of posts that contain the corresponding hashtag
     */
    @Override
    public List<Post> getPostsByHashtag(Hashtag hashtag) {
        final String SELECT_POSTS_BY_HASHTAG = "SELECT p.*\n" +
                "FROM post p\n" +
                "JOIN post_hashtag ph \n" +
                "ON ph.postId = p.id \n" +
                "WHERE ph.hashtagId = ?;";
        List<Post> allPostsByHashtag = jdbc.query(SELECT_POSTS_BY_HASHTAG, new PostMapper(), hashtag.getId());
        addHashtagsToList(allPostsByHashtag);
        return allPostsByHashtag;
    }

    /**
     * adds a new Post to the database
     *
     * @param post to be added to the database
     * @return the post that was just added to the database
     */
    @Override
    @Transactional
    public Post addNewPost(Post post) {
        /**
         * use insert query and use it to insert the basic course information to the database
         */
        final String INSERT_POST = "INSERT INTO post(timePosted, title, blogBody, isApproved) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_POST,
                Timestamp.valueOf(post.getTimePosted()),
                post.getTitle(),
                post.getBlogBody(),
                post.isApproved());
        /**
         * get the ID and add it to the course object from the database
         */
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setId(newId);
        /**
         * loops thorugh the list of each hashtag in the post and adds database entries to post_hashtag bridge table
         */
        insertPostHashtag(post);
        return post;
    }

    /**
     * loops thorugh the list of each hashtag in the post and adds database entries to post_hashtag bridge table
     */
    private void insertPostHashtag(Post post) {
        final String INSERT_POST_HASHTAG = "INSERT INTO "
                + "post_hashtag(postId, hashtagId) VALUES(?,?)";
        for(Hashtag ht : post.getHashtags()) {
            jdbc.update(INSERT_POST_HASHTAG,
                    post.getId(),
                    ht.getId());
        }
    }

    /**
     * modifies some aspect of the post in the database
     *
     * @param post the post will all the relevant changes
     */
    @Override
    @Transactional
    public void editPost(Post post) {
        final String UPDATE_POST = "UPDATE post SET timePosted = ?, title = ?, blogBody = ? "
                + "isApproved = ? WHERE id = ?";
        jdbc.update(UPDATE_POST,
                Timestamp.valueOf(post.getTimePosted()),
                post.getTitle(),
                post.getBlogBody(),
                post.isApproved(),
                post.getId());

        final String DELETE_POST_HASHTAG = "DELETE FROM post_hashtag WHERE postId = ?";
        jdbc.update(DELETE_POST_HASHTAG, post.getId());
        insertPostHashtag(post);
    }

    /**
     * removes a post from the database based on some given id
     *
     * @param id of the post to be removed
     */
    @Override
    @Transactional
    public void deletePostById(int id) {
        final String DELETE_POST_HASHTAG = "DELETE FROM post_hashtag WHERE postId = ?";
        jdbc.update(DELETE_POST_HASHTAG, id);

        final String DELETE_POST = "DELETE FROM post WHERE id = ?";
        jdbc.update(DELETE_POST, id);
    }

    public static final class PostMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int index) throws SQLException {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTimePosted(LocalDateTime.parse("timePosted"));
            post.setTitle(rs.getString("title"));
            post.setBlogBody(rs.getString("blogBody"));
            post.setApproved(rs.getBoolean("isApproved"));
            return post;
        }
    }
}
