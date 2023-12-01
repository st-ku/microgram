package com.stku.microgram.model;

import lombok.Data;

@Data
public class SimpleUserModel implements InternalUser{
    private String username;
    private String password;
}
