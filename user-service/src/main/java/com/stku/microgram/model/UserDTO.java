package com.stku.microgram.model;

import java.io.Serializable;

public record UserDTO(String name, String password) implements Serializable {
}
