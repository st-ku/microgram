package com.stku.microgram.service;

import com.stku.microgram.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Post> getAllPostByUser(String userId) {
        List<Post> posts = restTemplate.getForObject("http://localhost:8083/post/" + userId, List.class);
        return posts;
    }
}
