/*
* Written by Robert Forristall
* */

package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

/*
* User Database Configuration
* - Used to load starting/testing data into the database
*
* commandLineRunner: Uses the user repository interface and a
*   generated user to save a testing user into the database
*
* */

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
                    "$2a$04$2J2uvPv8U4DeRdFMSi61wu2/mJ9uN.9xb.3Cy3S4bUD0hTH4134QG"
            );

            userRepository.save(new_user);
        };
    }
}
