package com.stku.microgram.rest;

import com.stku.microgram.entity.User;
import com.stku.microgram.model.InternalUser;
import com.stku.microgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public InternalUser getUserById(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserById(id, (User) userDetails);
    }

    @GetMapping
    public List<InternalUser> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getAllUsers((User) userDetails);
    }

    @PostMapping
    public InternalUser createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public InternalUser updateUser(@PathVariable String id, @RequestBody User user, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.updateUser(id, user, (User) userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(id, (User) userDetails);
    }
}

