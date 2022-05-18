/*
* Written By Robert Forristall
* */

package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
* User API Controller (api/v1/user)
* - Handles all incoming api requests related to the user database
*
* - POST, /login: Handled by spring security and is used for logging in and getting a token
* - GET, /emailCheck: Checks to make sure the email address being used to sign up is valid and
*       sends an email to the new email address for verification
* - POST, /signup: Signs up a new user into the database
* */

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    private static UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService;}

    //@PostMapping(path = "login")
    //public User loginUser(@RequestBody User user) {return userService.loginUser(user.getEmail(), user.getPass());}

    @GetMapping(path = "emailCheck")
    public Integer checkForEmail(@RequestParam String email) {return userService.checkEmail(email);}

    @PostMapping(path = "signup")
    public void registerUser(@RequestBody User user) {userService.signupUser(user);}

}
