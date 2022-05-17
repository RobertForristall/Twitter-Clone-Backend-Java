package com.shareclub.TwitterCloneBackendJava.tweet;

import com.shareclub.TwitterCloneBackendJava.s3.S3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class TweetService {

    private final TweetRepository tweetRepository;

    private final static S3 s3 = new S3();

    @Autowired
    public TweetService(TweetRepository tweetRepository) { this.tweetRepository = tweetRepository;}

    public List<Tweet> getAllTweets() {

        return tweetRepository.findAll();

    }

    public List<Tweet> getAllTweetsByPoster_id(long id) {

        /*
        Optional<List<Tweet>> tweets = tweetRepository.findAllByPosterId(id);

        if (tweets.isEmpty()) {
            List<Tweet> empty_list = new ArrayList<Tweet>();

            return empty_list;
        }

        return tweets.get();

         */

        List<Tweet> empty_list = new ArrayList<Tweet>();

        return empty_list;

    }

    public List<Tweet> getAllTweetsByEmail(String email){


        Optional<List<Tweet>> tweets = tweetRepository.findAllByEmail(email);

        if (tweets.isEmpty()) {

            log.info("No tweets found, returning empty list...");
            List<Tweet> empty_list = new ArrayList<Tweet>();

            return empty_list;
        }

        if (tweets.get().size() == 0){
            log.info("No tweets found, returning empty list...");
            List<Tweet> empty_list = new ArrayList<Tweet>();

            return empty_list;
        }

        log.info("Tweets found, returning in list...");
        //log.info(tweets.get().toString());
        return tweets.get();

    }

    public void saveTweet(Tweet tweet) {

        // Check for all necessary fields
        /*if (tweet.getPosterId() <= 0) {
            throw new IllegalStateException("Must provide a valid poster id");
        }*/
        if (tweet.getMsg().length() == 0 && tweet.getSharedContent() == "None"){
            throw new IllegalStateException("A tweet can not be empty. Please provide some content");
        }
        if (tweet.getOriginalPoster() <= 0) {
            throw new IllegalStateException("Must provide the original poster's id");
        }

        tweetRepository.save(tweet);

    }

    public void updateTweet(Tweet tweet) {

    }
}
