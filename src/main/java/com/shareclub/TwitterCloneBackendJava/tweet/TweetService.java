package com.shareclub.TwitterCloneBackendJava.tweet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) { this.tweetRepository = tweetRepository;}

    public List<Tweet> getAllTweets() {

        return tweetRepository.findAll();

    }

    public List<Tweet> getAllTweetsByEmail(String email) {

        Optional<List<Tweet>> tweets = tweetRepository.findAllByEmail(email);

        if (tweets.isEmpty()) {
            List<Tweet> empty_list = new ArrayList<Tweet>();

            return empty_list;
        }

        return tweets.get();

    }

    public void saveTweet(Tweet tweet) {

        // Check for all necessary fields



    }
}
