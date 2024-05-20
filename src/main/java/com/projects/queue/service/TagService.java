package com.projects.queue.service;

import com.projects.queue.model.Tag;

import java.util.List;

public interface TagService {
    Tag findByName(String name);

    void addTag(String name);

    void deleteTag(String name);

    List<Tag> getAllTags();
}
