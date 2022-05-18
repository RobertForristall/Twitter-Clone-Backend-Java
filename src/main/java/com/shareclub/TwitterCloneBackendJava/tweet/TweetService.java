package com.shareclub.TwitterCloneBackendJava.tweet;

import com.shareclub.TwitterCloneBackendJava.s3.S3;
import com.shareclub.TwitterCloneBackendJava.wrappers.TweetWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
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

    public void saveTweet(TweetWrapper tweetWrapper) {

        Tweet tweet = tweetWrapper.getTweet();

        //MultipartFile file = tweetWrapper.getFile();

        // Check for all necessary fields
        /*if (tweet.getPosterId() <= 0) {
            throw new IllegalStateException("Must provide a valid poster id");
        }*/
        if (tweet.getMsg().length() == 0 && tweet.getSharedContent().equals("None")){
            throw new IllegalStateException("A tweet can not be empty. Please provide some content");
        }
        if (!tweet.getSharedContent().equals("None") && tweet.getFileKey().length() == 0) {
            throw new IllegalStateException("A tweet with content should have a contentKey");
        }
        if (tweet.getOriginalPoster().length() == 0) {
            throw new IllegalStateException("Must provide the original poster's email");
        }

        tweetRepository.save(tweet);

        if (tweet.getSharedContent().equals("Image")) {
            //s3.uploadFile(tweet.getFileKey(), file);
        }

    }

    public void updateTweet(Tweet tweet) {

    }
}
