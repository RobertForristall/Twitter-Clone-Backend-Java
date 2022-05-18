/*
* Written By Robert Forristall
* */

package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
* User Repository Interface
* - Utilizes Spring JPA to create and manage the user table in the
*       MySQL instance
*
* - FindUserByEmailAndPass: Finds a user in the databasse using their email and password
*       * No longer used *
*
* - findUSerByEmail: Finds a user in the database using their email
* */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndPass(String email, String pass);

    Optional<Integer> countUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
