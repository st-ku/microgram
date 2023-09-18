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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "followed_user_id", referencedColumnName = "id")
    @NotNull
    @JsonProperty("followed_user")
    private User followedUser;

    @Column(name = "created_at")
    private Long createdAt;
}
