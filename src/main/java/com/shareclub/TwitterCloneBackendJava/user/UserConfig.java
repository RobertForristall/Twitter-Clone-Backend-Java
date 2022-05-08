package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User new_user = new User(
                "tester@test.com",
                "Tester Testington",
                    LocalDate.of(1994, Month.JANUARY, 7),
                    true,
                    "testing"
            );

            userRepository.save(new_user);
        };
    }
}
