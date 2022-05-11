package com.shareclub.TwitterCloneBackendJava.tweet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class TweetConfig {

    @Bean
    CommandLineRunner commandLineRunnerTweet(TweetRepository tweetRepository) {
        return args -> {
            Tweet tweet = new Tweet(
              "tester@test.com",
              "This is a test tweet!",
              "Picture",
              0,
              0,
              new Date(),
              "tester@test.com"
            );

            tweetRepository.save(tweet);
        };
    }
}
