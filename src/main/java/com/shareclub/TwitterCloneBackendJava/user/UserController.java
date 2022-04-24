package com.shareclub.TwitterCloneBackendJava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1/user")
public class UserController {

    private static UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService;}

    @GetMapping
    public User loginUser(@RequestParam String email, @RequestParam String pass) {return userService.loginUser(email, pass);}

    @GetMapping(path = "emailCheck")
    public Integer checkForEmail(@RequestParam String email) {return userService.checkEmail(email);}

    @PostMapping
    public void registerUser(@RequestBody User user) {userService.signupUser(user);}
}
