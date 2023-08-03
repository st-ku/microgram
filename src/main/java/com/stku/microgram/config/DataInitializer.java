package com.stku.microgram.config;

import com.stku.microgram.model.User;
import com.stku.microgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        createStubData();
    }

    private void createStubData() {
        // Create and save stub data for testing purposes
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        userService.createUser(user1);

        User user2 = new User();
        user2.setId(UUID.randomUUID().toString());
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        userService.createUser(user2);
    }
}

