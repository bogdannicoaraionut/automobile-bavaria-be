package com.automobilebavaria.backend.controller;

import com.automobilebavaria.backend.dto.CreateUserRequest;
import com.automobilebavaria.backend.dto.LoginRequest;
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

    @PostMapping("/create-account")
    public ResponseEntity<User> createAccount(@Valid @RequestBody CreateUserRequest request) {
        User newUser = userService.createUser(request);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    private ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
