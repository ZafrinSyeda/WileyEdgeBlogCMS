package com.company.blog.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private LocalDateTime timePosted;
    @NotBlank(message = "Title must not be empty.")
    @Size(max = 75, message="Title must be less than 75 characters.")
    private String title;
    @NotBlank(message = "Blog body must not be empty.")
    private String  blogBody;
    private boolean isApproved;
    private List<Hashtag> hashtags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", timePosted=" + timePosted +
                ", title='" + title + '\'' +
                ", blogBody='" + blogBody + '\'' +
                ", isApproved=" + isApproved +
                ", hashtags=" + hashtags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return getId() == post.getId() && isApproved() == post.isApproved() && Objects.equals(getTimePosted(), post.getTimePosted()) && Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getBlogBody(), post.getBlogBody()) && Objects.equals(getHashtags(), post.getHashtags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTimePosted(), getTitle(), getBlogBody(), isApproved(), getHashtags());
    }
}
