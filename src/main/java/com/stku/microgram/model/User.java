package com.stku.microgram.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    private String id;

    private String username;
    private String password;
    @Id
    private String email;

    @Column(name = "created_at")
    private Long createdAt;
}
