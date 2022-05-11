package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
