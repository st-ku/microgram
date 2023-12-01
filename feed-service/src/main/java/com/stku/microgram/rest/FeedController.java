package com.stku.microgram.rest;

import com.stku.microgram.entity.Post;
import com.stku.microgram.model.UserDTO;
import com.stku.microgram.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Resource
    private FollowService followService;

    @GetMapping
    public List<Post> getFeed(UserDTO user) {
        return followService.prepareFeed(user);
    }
}
