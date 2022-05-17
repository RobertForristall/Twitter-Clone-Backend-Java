package com.shareclub.TwitterCloneBackendJava.image;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfig {

    @Bean
    CommandLineRunner commandLineRunnerImage (ImageRepository imageRepository) {
        return args -> {

            Image img = new Image("Test-path");

            imageRepository.save(img);

        };

    }
}
