package com.shareclub.TwitterCloneBackendJava.tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) { this.tweetService = tweetService; }

    @GetMapping(path = "all")
    public List<Tweet> getAllTweets() {return tweetService.getAllTweets();}

    @GetMapping(path = "email")
    public List<Tweet> getAllTweetsByEmail(@RequestParam String email) {return tweetService.getAllTweetsByEmail(email);}

    @PostMapping(path = "add")
    public void saveTweet(@RequestBody Tweet tweet) {tweetService.saveTweet(tweet);}

}
