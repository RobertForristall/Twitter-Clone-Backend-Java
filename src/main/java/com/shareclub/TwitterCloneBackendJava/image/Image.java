package com.shareclub.TwitterCloneBackendJava.image;

import com.shareclub.TwitterCloneBackendJava.tweet.Tweet;

import javax.persistence.*;

@Entity
@Table

public class Image {

    @Id
    @SequenceGenerator(
            name="image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private long id;

    @OneToOne
    private Tweet tweet;

    @Column(nullable = true, length = 64)
    private String image;
}
