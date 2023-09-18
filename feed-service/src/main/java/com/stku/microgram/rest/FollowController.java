package com.stku.microgram.rest;

import com.stku.microgram.entity.Follow;
import com.stku.microgram.entity.User;
import com.stku.microgram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("/{id}")
    public Follow getFollowById(@PathVariable Long id) {
        return followService.getFollowById(id);
    }

    @GetMapping
    public List<Follow> getAllFollows() {
        return followService.getAllFollows();
    }

    @PostMapping("/{userId}")
    public Follow createFollow(@PathVariable String userId, @AuthenticationPrincipal UserDetails userDetails) {
        return followService.createFollow(userId, (User) userDetails);
    }

    @PutMapping
    public Follow updateFollow(@RequestBody Follow follow, @AuthenticationPrincipal UserDetails userDetails) {
        return followService.updateFollow(follow, (User) userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFollow(@PathVariable Long id) {
        followService.deleteFollow(id);
    }
}
