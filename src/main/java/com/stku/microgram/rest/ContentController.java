package com.stku.microgram.rest;

import com.stku.microgram.entity.Content;
import com.stku.microgram.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable Integer id) {
        return contentService.getContentById(id);
    }

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @PutMapping("/{id}")
    public Content updateContent(@PathVariable Integer id, @RequestBody Content content) {
        return contentService.updateContent(id, content);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        contentService.deleteContent(id);
    }
}
