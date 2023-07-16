package com.geekster.InstaBackend.controllers;

import com.geekster.InstaBackend.models.User;
import com.geekster.InstaBackend.models.dto.SignInInput;
import com.geekster.InstaBackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid User user){
        return userService.signUp(user);
    }
    @PostMapping("/signin")
    public String signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }
    @PutMapping("/update/{id}/{email}")
    public String updateUserInfo(@PathVariable Integer id,@PathVariable String email,@RequestBody User user){
        return userService.updateUserInfo(id,email,user);
    }
    @DeleteMapping("/signout")
    public String signOut(@RequestParam String email,@RequestParam String token){
        return userService.signOut(email,token);
    }

}
