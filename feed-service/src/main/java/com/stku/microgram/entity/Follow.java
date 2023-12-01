package com.stku.microgram.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "follow")
@Data
@NoArgsConstructor
public class Follow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    @JsonProperty("followed_user")
    private String followedUser;

    @Column(name = "created_at")
    private Long createdAt;
}
