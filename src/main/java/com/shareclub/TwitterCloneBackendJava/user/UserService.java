package com.shareclub.TwitterCloneBackendJava.user;

import com.shareclub.TwitterCloneBackendJava.EmailValidation;
import com.shareclub.TwitterCloneBackendJava.role.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}


    public User loginUser(String email, String pass) {
        log.info("Logging in user with email: {}", email);
        Optional<User> userByEmailAndPass = userRepository.findUserByEmailAndPass(email, pass);

        if (userByEmailAndPass.isPresent()){
            log.info("User successfully logged in: {}", userByEmailAndPass.get());
            return userByEmailAndPass.get();
        }
        else {
            log.info("Error logging in user!");
            throw new IllegalStateException("Entered Credentials dont match records!");
        }
    }

    public void signupUser(User user) {

        log.info("Signing up user: {}", user.toString());

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

        log.info("Saving User...");
        userRepository.save(user);

        log.info("User saved!");
    }

    public Integer checkEmail(String email) {

        log.info("Validating email address for user: {}", email);

        Optional<User> checkForUser = userRepository.findUserByEmail(email);

        if (checkForUser.isPresent()){
            throw new IllegalStateException("Email taken! Please use a different email or sign in with your existing email.");
        }

        log.info("Email verified!");
        return 1;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Role base_role = new Role(1, "ROLE_USER");

        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found! user: {}", user.get());
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(base_role.getName()));


        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPass(), authorities);

    }
}
