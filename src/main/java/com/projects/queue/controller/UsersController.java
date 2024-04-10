package com.projects.queue.controller;

import com.projects.queue.DTOs.user.CreateUserDTO;
import com.projects.queue.DTOs.user.LoginDTO;
import com.projects.queue.DTOs.user.UpdateUserDTO;
import com.projects.queue.model.User;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDTO createUserDTO) {
        CreateUserDTO createdUser = userService.createUser(createUserDTO);
        if (createdUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        User loggedInUser = userService.getUserByEmail(user.getEmail());
        if (loggedInUser == null) {
            return ResponseEntity.badRequest().build();
        }

        String storedEncryptedPassword = loggedInUser.getPassword();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(user.getPassword(), storedEncryptedPassword)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO createUserDTO) {
        userService.updateUser(createUserDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
