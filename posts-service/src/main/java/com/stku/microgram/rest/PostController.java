package com.stku.microgram.rest;

import com.stku.microgram.entity.Post;
import com.stku.microgram.entity.User;
import com.stku.microgram.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Post createPost(@ModelAttribute Post post, @RequestParam("files") MultipartFile[] files, @AuthenticationPrincipal UserDetails userDetails) {
        return postService.createPost(post, files, (User) userDetails);
    }

    @PutMapping()
    public Post updatePost(@ModelAttribute Post post, @RequestParam("files") MultipartFile[] files, @AuthenticationPrincipal UserDetails userDetails) {
        return postService.updatePost(post, files, (User) userDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, (User) userDetails);
    }
}

