package com.stku.microgram.repository;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Repository
public class CloudinaryRepository {
    @Resource
    private Cloudinary cloudinary;

    private Map upload(MultipartFile file) throws IOException {
                return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
    }

}
