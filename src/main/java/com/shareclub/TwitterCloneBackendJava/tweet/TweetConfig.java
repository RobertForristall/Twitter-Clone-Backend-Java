package com.shareclub.TwitterCloneBackendJava.tweet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TweetConfig {

    @Bean
    CommandLineRunner commandLineRunner(TweetRepository tweetRepository) {
        return args -> {
            Tweet tweet = new Tweet(
              "tester@test.com",
              "This is a test tweet!",
              "Picture",
              0,
              0
            );

            tweetRepository.save(tweet);
        };
    }
}
