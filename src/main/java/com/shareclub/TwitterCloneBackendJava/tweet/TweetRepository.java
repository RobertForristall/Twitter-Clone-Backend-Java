package com.shareclub.TwitterCloneBackendJava.tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    //Optional<List<Tweet>> findAllByPosterId(long poster_id);

    Optional<List<Tweet>> findAllByEmail(String email);

}
