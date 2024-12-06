package com.example.backend_album_discovery.controller;

import com.example.backend_album_discovery.model.User;
import com.example.backend_album_discovery.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {

        return userService.getUserById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUserInfo(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUserInfo(id,updatedUser);
    }

}
