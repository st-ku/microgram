package com.stku.microgram.rest;

import com.stku.microgram.entity.Post;
import com.stku.microgram.model.UserDTO;
import com.stku.microgram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/user/{userId}")
    public List<Post> getAllPostsByUser(@PathVariable String userId) {
        return postService.getAllPostsByUser(userId);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Post createPost(@ModelAttribute Post post, @ModelAttribute UserDTO userDTO, @RequestParam("files") MultipartFile[] files) {
        return postService.createPost(post, userDTO, files);
    }

    @PutMapping()
    public Post updatePost(@ModelAttribute Post post, @ModelAttribute UserDTO userDTO, @RequestParam("files") MultipartFile[] files) {
        return postService.updatePost(post, userDTO, files);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }
}

