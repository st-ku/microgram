package com.stku.microgram.repository;

import com.stku.microgram.entity.Post;
import com.stku.microgram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByUser(User user);
}

