package com.stku.microgram.service;

import com.stku.microgram.entity.Post;
import com.stku.microgram.model.UserDTO;
import com.stku.microgram.repository.CloudinaryRepository;
import com.stku.microgram.repository.PostRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class PostService {

    @Resource
    private PostRepository postRepository;
    @Resource
    private CloudinaryRepository cloudinaryRepository;
    @Resource
    private UserService userService;

    public Post getPostById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsByUser(UserDTO userModel) {
        return postRepository.findAllByUserId(userModel.name());
    }

    public Post createPost(Post post, UserDTO userDTO, MultipartFile[] files) {
        if (!userService.isAuth(userDTO.name(), userDTO.password())) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        post.setId(UUID.randomUUID().toString());
        post.setUserId(userDTO.name());
        post.setCreatedAt(new Date().getTime());
        List<String> photoUrls = uploadToCloudinary(files);
        post.setFileUrls(photoUrls);
        return postRepository.saveAndFlush(post);
    }

    private List<String> uploadToCloudinary(MultipartFile[] files) {
        List<String> photoUrls = new LinkedList<>();
        for (MultipartFile file : files) {
            {
                try {
                    photoUrls.add(cloudinaryRepository.upload(file.getResource().getContentAsByteArray()).get("url").toString());
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return photoUrls;
    }

    public Post updatePost(Post post, UserDTO activeUserModel,  MultipartFile[] files) {
        Post currentPost = getPostById(post.getId());
        if (!currentPost.getUserId().equals(activeUserModel.name())) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        List<String> photoUrls = uploadToCloudinary(files);
        post.setFileUrls(photoUrls);
        post.setUpdatedAt(new Date().getTime());
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}

