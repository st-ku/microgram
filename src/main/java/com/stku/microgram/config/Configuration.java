package com.stku.microgram.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "drecubhij",
                "api_key", "632798356156311",
                "api_secret", "BYQ1dcsgFw2K0jjENRd_ttUaxfo"));
    }
}
