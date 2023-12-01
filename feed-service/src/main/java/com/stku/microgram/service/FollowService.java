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

    public Follow getFollowById(Long id) {
        Optional<Follow> optionalFollow = followRepository.findById(id);
        return optionalFollow.orElse(null);
    }

    public List<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    public Follow createFollow(String userToFollowId, UserDTO activeUser) {
        Follow follow = new Follow();
        follow.setUser(activeUser.name());
        follow.setFollowedUser(userToFollowId);
        return followRepository.save(follow);
    }

    public Follow updateFollow(Follow follow, UserDTO activeUser) {
        follow.setUser(activeUser.name());
        return followRepository.save(follow);
    }

    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }

    public List<Post> prepareFeed(UserDTO activeUser) {
        List<Follow> follows = followRepository.findAllByUser(activeUser.name());
        List<Post> posts = new LinkedList<>();
        follows.forEach(follow -> posts.
                addAll(postService.
                        getAllPostsByUser(follow.getFollowedUser())));
        return posts;
    }
}

