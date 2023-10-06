package com.stku.microgram.service;

import com.stku.microgram.entity.Post;
import com.stku.microgram.entity.User;
import com.stku.microgram.repository.CloudinaryRepository;
import com.stku.microgram.repository.PostRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class PostService {

    @Resource
    private PostRepository postRepository;
    @Resource
    private CloudinaryRepository cloudinaryRepository;

    public Post getPostById(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    public Post createPost(Post post, MultipartFile[] files, User activeUser) {
        post.setId(UUID.randomUUID().toString());
        post.setUser(activeUser);
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

    public Post updatePost(Post post, MultipartFile[] files, User activeUser) {
        Post currentPost = getPostById(post.getId());
        if (!currentPost.getUser().getId().equals(activeUser.getId())) {
            throw new RuntimeException("You are not authorized to access this resource");
        }
        List<String> photoUrls = uploadToCloudinary(files);
        post.setFileUrls(photoUrls);
        post.setUpdatedAt(new Date().getTime());
        return postRepository.save(post);
    }

    public void deletePost(String id, User activeUser) {
        postRepository.deleteById(id);
    }
}

