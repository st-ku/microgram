package com.stku.microgram.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "post")
@Data
public class Post implements Serializable {

    @Id
    @NotNull
    private String id;
    @NotNull
    private String title;
    @NotNull
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @ElementCollection
    private Collection<String> fileUrls;
}
