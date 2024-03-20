package com.projects.queue.service;

import com.projects.queue.DTOs.CreateUserDTO;
import com.projects.queue.model.AccountStatus;
import com.projects.queue.model.User;
import com.projects.queue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserDTO.getPassword()));
        user.setName(createUserDTO.getName());
        user.setRole(createUserDTO.getRole());
        user.setScore(0);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setBio("Hello! I'm a user");

        userRepository.save(user);

        CreateUserDTO createdUser = new CreateUserDTO();
        createdUser.setId(user.getId());
        return createdUser;
    }
}
