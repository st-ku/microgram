package com.stku.microgram.model;

import lombok.Data;

@Data
public class SimpleUserModel implements InternalUser{
    private String id;
    private String username;
    private String password;
    private String email;
}
