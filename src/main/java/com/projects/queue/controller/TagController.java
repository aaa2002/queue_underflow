package com.projects.queue.controller;

import com.projects.queue.model.Tag;
import com.projects.queue.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public void addTag(@RequestBody String name) {
        tagService.addTag(name);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteTag(@RequestParam String name) {
        tagService.deleteTag(name);
    }

    @GetMapping("/all")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
}
