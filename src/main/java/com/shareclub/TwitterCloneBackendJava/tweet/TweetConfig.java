package com.shareclub.TwitterCloneBackendJava.tweet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class TweetConfig {

    @Bean
    CommandLineRunner commandLineRunnerTweet(TweetRepository tweetRepository) {
        return args -> {

            List<Tweet> tweets = new ArrayList<>();

            Tweet tweet = new Tweet(
              1,
              "This is a test tweet!",
              "None",
              0,
              0,
              new Date(),
              "tester@test.com",
                    "tester@test.com",
                    null
            );

            Tweet tweet2 = new Tweet(
                    1,
                    "Welcome to share club, I hope you have a wonderful time on the platform." +
                            "This is just a welcome message for testing and to get people interested in the site." +
                            "There will likely be many more messages like this one showing off the webiste's capabilities." +
                            "Like the picture shown below: ",
                    "Picture",
                    10,
                    5,
                    new Date(),
                    "tester@test.com",
                    "tester@test.com",
                    "test-key"
            );

            tweets.add(tweet);
            tweets.add(tweet2);

            //tweetRepository.save(tweet);
            tweetRepository.saveAll(tweets);

        };
    }
}
