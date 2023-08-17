package com.stku.microgram.service;

import com.stku.microgram.entity.Content;
import com.stku.microgram.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public Content getContentById(Integer id) {
        Optional<Content> optionalContent = contentRepository.findById(id);
        return optionalContent.orElse(null);
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Content updateContent(Integer id, Content content) {
        content.setId(id);
        return contentRepository.save(content);
    }

    public void deleteContent(Integer id) {
        contentRepository.deleteById(id);
    }
}
