package com.stku.microgram.rest;

import com.stku.microgram.model.Follow;
import com.stku.microgram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/follow")
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

    @PostMapping
    public Follow createFollow(@RequestBody Follow follow) {
        return followService.createFollow(follow);
    }

    @PutMapping("/{id}")
    public Follow updateFollow(@PathVariable Long id, @RequestBody Follow follow) {
        return followService.updateFollow(id, follow);
    }

    @DeleteMapping("/{id}")
    public void deleteFollow(@PathVariable Long id) {
        followService.deleteFollow(id);
    }
}
