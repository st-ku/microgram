package com.stku.microgram.service;

import com.stku.microgram.entity.Follow;
import com.stku.microgram.entity.Post;
import com.stku.microgram.entity.User;
import com.stku.microgram.repository.FollowRepository;
import com.stku.microgram.repository.UserRepository;
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
    private UserRepository userRepository;

    public Follow getFollowById(Long id) {
        Optional<Follow> optionalFollow = followRepository.findById(id);
        return optionalFollow.orElse(null);
    }

    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    public Follow createFollow(String userToFollowId, User activeUser) {
        User userToFollow = userRepository.findById(userToFollowId).orElseThrow();
        Follow follow = new Follow();
        follow.setUser(activeUser);
        follow.setFollowedUser(userToFollow);
        return followRepository.save(follow);
    }

    public Follow updateFollow(Follow follow, User activeUser) {
        follow.setUser(activeUser);
        return followRepository.save(follow);
    }

    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }

    public List<Post> prepareFeed(User activeUser) {
        List<Follow> follows = followRepository.findAllByUser(activeUser);
        List<Post> posts = new LinkedList<>();
        follows.forEach(follow -> posts.
                addAll(postService.
                        getAllPostsByUser(follow.getFollowedUser())));
        return posts;
    }
}

