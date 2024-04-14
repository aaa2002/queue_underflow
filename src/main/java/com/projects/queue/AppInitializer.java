package com.projects.queue;

import com.projects.queue.DTOs.user.CreateUserDTO;
import com.projects.queue.model.Role;
import com.projects.queue.model.User;
import com.projects.queue.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize() {
        // Check if a default user profile exists
        User existingUser = userService.getUserByEmail("default@example.com");
        if (existingUser == null) {
            CreateUserDTO createUserDTO = new CreateUserDTO();
            createUserDTO.setEmail("aaa");
            createUserDTO.setPassword("aaa");
            createUserDTO.setName("aaa");
            createUserDTO.setRole(Role.MODERATOR);
            userService.createUser(createUserDTO);
        }
    }
}
