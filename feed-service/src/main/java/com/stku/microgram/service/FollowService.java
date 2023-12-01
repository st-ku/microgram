package com.stku.microgram.service;

import com.stku.microgram.entity.Follow;
import com.stku.microgram.entity.Post;
import com.stku.microgram.model.UserDTO;
import com.stku.microgram.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public Follow getFollowById(Long id) {
        Optional<Follow> optionalFollow = followRepository.findById(id);
        return optionalFollow.orElse(null);
    }

    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    public Follow createFollow(String userToFollowId, UserDTO activeUser) {
        Follow follow = new Follow();
        follow.setUserId(activeUser.name());
        follow.setFollowedUser(userToFollowId);
        return followRepository.save(follow);
    }

    public Follow updateFollow(Follow follow, UserDTO activeUser) {
        follow.setUserId(activeUser.name());
        return followRepository.save(follow);
    }

    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }

    public List<Post> prepareFeed(UserDTO activeUser) {
        List<Follow> follows = followRepository.findAllByUserId(activeUser.name());
        List<Post> posts = new LinkedList<>();
        if (userService.isAuth(activeUser.name(), activeUser.password())) {
            follows.forEach(follow -> posts.
                    addAll(postService.getAllPostByUser(follow.getFollowedUser())));
        } else {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        return posts;
    }
}

