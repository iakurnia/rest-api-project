package com.example.restapiproject.controller;

import com.example.restapiproject.model.User;
import com.example.restapiproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        List<User> users;
        try {
            users = userService.getAll();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/getAll/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsersById(@PathVariable("id") long id) {
        User users;
        try {
            users = userService.getListUserById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") long id,@RequestBody User user) throws Exception {
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("id not found");
        }
        return userService.updateUser(id,user);
    }

    @PutMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id") long id) throws Exception {
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("id not found");
        }
        return userService.delete(id);
    }
}
