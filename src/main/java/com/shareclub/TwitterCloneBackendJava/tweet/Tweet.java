package com.shareclub.TwitterCloneBackendJava.tweet;

import com.shareclub.TwitterCloneBackendJava.user.User;

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

    //private long posterId;

    private String email;

    private String msg;

    private String sharedContent;

    private Integer likes;

    private  Integer retweets;

    private Date datePosted;

    private long originalPosterId;

    @ManyToOne
    @JoinColumn(name = "posterId")
    private User user;


    public Tweet() {
    }

    public Tweet(long posterId, String msg, String sharedContent, int likes, int retweets, Date datePosted, long original_poster, String email) {
        //this.posterId = posterId;
        this.msg = msg;
        this.sharedContent = sharedContent;
        this.likes = likes;
        this.retweets = retweets;
        this.datePosted = datePosted;
        this.originalPosterId = original_poster;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    //public long getPosterId() {return this.posterId; }

    //public  void setPosterId(long posterId) {this.posterId = posterId;}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSharedContent() {
        return sharedContent;
    }

    public void setSharedContent(String sharedContent) {
        this.sharedContent = sharedContent;
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

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public long getOriginalPoster() {
        return originalPosterId;
    }

    public void setOriginalPoster(long original_poster) {
        this.originalPosterId = original_poster;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                //", posterId=" + posterId +
                ", msg='" + msg + '\'' +
                ", sharedContent='" + sharedContent + '\'' +
                ", likes=" + likes +
                ", retweets=" + retweets +
                ", datePosted=" + datePosted +
                ", originalPosterId=" + originalPosterId +
                '}';
    }
}
