package com.shareclub.TwitterCloneBackendJava.wrappers;

import com.shareclub.TwitterCloneBackendJava.tweet.Tweet;

import java.awt.image.BufferedImage;
import java.io.File;

public class TweetWrapper {

    private Tweet tweet;

    private File file;

    public TweetWrapper() {
    }

    public TweetWrapper(Tweet tweet, File file) {
        this.tweet = tweet;
        this.file = file;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}


