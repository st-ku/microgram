package com.stku.microgram.repository;

import com.stku.microgram.entity.Follow;
import com.stku.microgram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByUser(User user);
}
