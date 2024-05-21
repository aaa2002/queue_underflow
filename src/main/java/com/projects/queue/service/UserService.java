package com.projects.queue.service;

import com.projects.queue.DTOs.user.CreateUserDTO;
import com.projects.queue.DTOs.user.UpdateUserDTO;
import com.projects.queue.model.User;

import java.util.List;

public interface UserService {

    CreateUserDTO createUser(CreateUserDTO createUserDTO);

    List<User> getAllUsers();
    void deleteUserById(Long id);

    void updateUser(UpdateUserDTO updateUserDTO);

    User getUserByEmail(String email);
    User getUserById(Long id);

    void banUser(Long id);

    void unbanUser(Long id);
}
