package com.shareclub.TwitterCloneBackendJava.tweet;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Tweet {
    @Id
    @SequenceGenerator(
            name = "tweet_sequence",
            sequenceName = "tweet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tweet_sequence"
    )
    private long id;

    private String email;

    private String msg;

    private String shared_content;

    private Integer likes;

    private  Integer retweets;

    private Date date_posted;

    private String original_poster;

    public Tweet() {
    }

    public Tweet(String email, String msg, String shared_content, int likes, int retweets, Date date_posted, String original_poster) {
        this.email = email;
        this.msg = msg;
        this.shared_content = shared_content;
        this.likes = likes;
        this.retweets = retweets;
        this.date_posted = date_posted;
        this.original_poster = original_poster;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShared_content() {
        return shared_content;
    }

    public void setShared_content(String shared_content) {
        this.shared_content = shared_content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getRetweets() {
        return retweets;
    }

    public void setRetweets(Integer retweets) {
        this.retweets = retweets;
    }

    public Date getDate_posted() {
        return date_posted;
    }

    public void setDate_posted(Date date_posted) {
        this.date_posted = date_posted;
    }

    public String getOriginal_poster() {
        return original_poster;
    }

    public void setOriginal_poster(String original_poster) {
        this.original_poster = original_poster;
    }
}
