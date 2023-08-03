package com.stku.microgram.service;

import com.stku.microgram.model.Follow;
import com.stku.microgram.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Follow createFollow(Follow follow) {
        return followRepository.save(follow);
    }

    public Follow updateFollow(Long id, Follow follow) {
        follow.setId(id);
        return followRepository.save(follow);
    }

    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }
}

