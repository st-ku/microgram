package com.stku.microgram.service;

import com.stku.microgram.entity.User;
import com.stku.microgram.model.InternalUser;
import com.stku.microgram.model.SimpleUserModel;
import com.stku.microgram.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(String id, User activeUser) {
        if (!id.equals(activeUser.getId()) && !activeUser.getAuthorities().contains("ROLE_ADMIN")) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<InternalUser> getAllUsers(User activeUser) {
        if (activeUser.getRoles().contains("ROLE_ADMIN")) {
            List<User> users = userRepository.findAll();
            return new ArrayList<>(users);
        } else {
            return userRepository.findAll().stream().map(this::convert).collect(Collectors.toList());
        }
    }

    public User createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Long now = Instant.now().getEpochSecond();
        user.setCreatedAt(now);
        return userRepository.save(user);
    }

    public User updateUser(String id, User user, User activeUser) {
        if (!id.equals(activeUser.getId()) && !activeUser.getAuthorities().contains("ROLE_ADMIN")) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(String id, User activeUser) {
        if (id.equals(activeUser.getId()) && !activeUser.getAuthorities().contains("ROLE_ADMIN")) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }

    public SimpleUserModel convert(User user) {
        SimpleUserModel simpleUserModel = new SimpleUserModel();
        simpleUserModel.setId(user.getId());
        simpleUserModel.setUsername(user.getUsername());
        simpleUserModel.setEmail(user.getEmail());
        return simpleUserModel;
    }
}

