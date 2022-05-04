package com.shareclub.TwitterCloneBackendJava.user;

import com.shareclub.TwitterCloneBackendJava.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}


    public User loginUser(String email, String pass) {
        Optional<User> userByEmailAndPass = userRepository.findUserByEmailAndPass(email, pass);

        if (userByEmailAndPass.isPresent()){
            return userByEmailAndPass.get();
        }
        else {
            throw new IllegalStateException("Entered Credentials dont match records!");
        }
    }

    public void signupUser(User user) {
        if (user.getEmail().length() == 0 || EmailValidation.patternMatch(user.getEmail())) {
            throw new IllegalStateException("Please enter a valid email");
        } else if (user.getName().length() == 0) {
            throw new IllegalStateException("Please enter your name");
        } else if (user.getAge() < 13) {
            throw new IllegalStateException("Please ask a guardian for help signing up with us");
        } else if (!user.isIs_verified()) {
            throw new IllegalStateException("Email validation failed, please return and re-enter your token.");
        } else if (user.getPass().length() == 0) {
            throw new IllegalStateException("Please enter a valid password");
        }

        userRepository.save(user);
    }

    public Integer checkEmail(String email) {

        Optional<User> checkForUser = userRepository.findUserByEmail(email);

        System.out.println(checkForUser.isPresent());

        if (checkForUser.isPresent()){
            throw new IllegalStateException("Email taken! Please use a different email or sign in with your existing email.");
        }

        return 1;

    }
}
