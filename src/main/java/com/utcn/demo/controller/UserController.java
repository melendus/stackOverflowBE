package com.utcn.demo.controller;

import com.utcn.demo.entity.User;
import com.utcn.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }


    @GetMapping("/getById/{id}")
    @ResponseBody
    public User retrieveById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getByEmail/{email}")
    public Optional<User> retrieveUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseBody
    @Transactional
    public long deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @PutMapping("/updateUser")
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
