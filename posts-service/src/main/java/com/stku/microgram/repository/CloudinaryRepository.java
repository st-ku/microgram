package com.stku.microgram.repository;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class CloudinaryRepository {
    @Resource
    private Cloudinary cloudinary;

    public Map upload(byte[] file) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap());
    }

}
