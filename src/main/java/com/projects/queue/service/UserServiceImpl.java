package com.projects.queue.service;

import com.projects.queue.DTOs.user.CreateUserDTO;
import com.projects.queue.DTOs.user.UpdateUserDTO;
import com.projects.queue.model.AccountStatus;
import com.projects.queue.model.User;
import com.projects.queue.repository.AnswerRepository;
import com.projects.queue.repository.QuestionRepository;
import com.projects.queue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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
        user.setAvatar(createUserDTO.getAvatar());

        if(userRepository.findByEmail(createUserDTO.getEmail()) != null) {
            return null;
        }

        userRepository.save(user);

        CreateUserDTO createdUser = new CreateUserDTO();
        createdUser.setId(user.getId());
        return createdUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        answerRepository.deleteAllByUserId(id);
        questionRepository.deleteAllByUserId(id);
        userRepository.deleteById(id);
        System.out.println("User with id " + id + " deleted");
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(updateUserDTO.getId()).get();
        user.setEmail(updateUserDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(updateUserDTO.getPassword()));
        user.setName(updateUserDTO.getName());
        user.setRole(updateUserDTO.getRole());
        user.setScore(updateUserDTO.getScore());
        user.setAccountStatus(updateUserDTO.getAccountStatus());
        user.setBio(updateUserDTO.getBio());

        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
