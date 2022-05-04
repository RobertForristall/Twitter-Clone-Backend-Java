package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndPass(String email, String pass);

    Optional<Integer> countUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
