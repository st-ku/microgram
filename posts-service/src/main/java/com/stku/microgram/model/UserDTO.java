package com.stku.microgram.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

public record UserDTO(String name, String password) implements Serializable {
}
