package com.projects.queue.service;

import com.projects.queue.model.Tag;
import com.projects.queue.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    public void addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tagRepository.save(tag);
    }

    public void deleteTag(String name) {
        Tag tag = tagRepository.findByName(name);
        tagRepository.delete(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
