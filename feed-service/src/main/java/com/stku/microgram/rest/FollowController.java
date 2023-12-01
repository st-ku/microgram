package com.stku.microgram.rest;

import com.stku.microgram.entity.Follow;
import com.stku.microgram.model.UserDTO;
import com.stku.microgram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{userId}")
    public Follow createFollow(@PathVariable String userId, @ModelAttribute UserDTO userDTO) {
        return followService.createFollow(userId, userDTO);
    }

    @PutMapping
    public Follow updateFollow(@RequestBody Follow follow, @ModelAttribute UserDTO userDTO) {
        return followService.updateFollow(follow, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFollow(@PathVariable Long id, @ModelAttribute UserDTO userDTO) {
        followService.deleteFollow(id);
    }
}
