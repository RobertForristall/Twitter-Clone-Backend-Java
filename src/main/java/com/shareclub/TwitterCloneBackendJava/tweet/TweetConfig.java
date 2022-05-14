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
              1,
              "This is a test tweet!",
              "None",
              0,
              0,
              new Date(),
              1,
                    "tester@test.com"
            );

            tweetRepository.save(tweet);

        };
    }
}
