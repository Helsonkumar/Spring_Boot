package com.helson.spring_boot.learn_spring_boot;

import com.helson.spring_boot.learn_spring_boot.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class Controller1 {

    @Autowired
    UserDAO user_service;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello! Helsonkumar";
    }

    @GetMapping("/profile")
    public Profile getProfile() {
        return new Profile("Helsonkumar", 32, "Chennai");
    }

    @GetMapping("/profile/{name}/{activity}")
    public String getActivity(@PathVariable String name , @PathVariable String activity) {
        return String.format("%s activity is %s as of %s" , name , activity , LocalDateTime.now());
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
       return user_service.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserId(@PathVariable Integer id) throws UserNotFoundException {
            return user_service.retrieveUser(id);
    }


    //In this method we access the current request and respond with the URI applcable for the resource created.
    //Location : Technically indicates the URI to which the request must be redirected.
    @PostMapping("/user/add")
    public ResponseEntity<Object> addUser(@RequestBody  User user) {
        User new_user = user_service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new_user.getId()).toUri();
        return ResponseEntity.created(location).build();

    }


}
