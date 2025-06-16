package com.automobilebavaria.backend.service;

import com.automobilebavaria.backend.dto.CreateUserRequest;
import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.exception.AccountException;
import com.automobilebavaria.backend.mapper.UserMapper;
import com.automobilebavaria.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.email())) {
            throw new AccountException("Account with this email already exists");
        }
        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
