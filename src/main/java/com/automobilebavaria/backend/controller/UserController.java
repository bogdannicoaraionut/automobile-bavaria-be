package com.automobilebavaria.backend.controller;

import com.automobilebavaria.backend.dto.CreateUserRequest;
import com.automobilebavaria.backend.dto.LoginRequest;
import com.automobilebavaria.backend.dto.UpdateUserRequest;
import com.automobilebavaria.backend.dto.UserDTO;
import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createAccount(@Valid @RequestBody CreateUserRequest request) {
        User newUser = userService.createUser(request);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserInfo(username));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateAccount(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        User newUser = userService.updateUser(id, request);
        return ResponseEntity.ok(newUser);
    }
}
