package com.automobilebavaria.backend.service;

import com.automobilebavaria.backend.dto.CreateUserRequest;
import com.automobilebavaria.backend.dto.UpdateUserRequest;
import com.automobilebavaria.backend.dto.UserDTO;
import com.automobilebavaria.backend.entity.Role;
import com.automobilebavaria.backend.entity.User;
import com.automobilebavaria.backend.exception.AccountException;
import com.automobilebavaria.backend.exception.EntityNotFoundAlertException;
import com.automobilebavaria.backend.mapper.UserMapper;
import com.automobilebavaria.backend.repository.RoleRepository;
import com.automobilebavaria.backend.repository.UserRepository;
import com.automobilebavaria.backend.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @NonNull
    public User createUser(@NonNull CreateUserRequest request) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(request.email()))) {
            throw new AccountException("Account with this email already exists");
        }
        User user = userMapper.toUser(request);
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(new HashSet<>(Set.of(defaultRole)));

        return userRepository.save(user);
    }

    @NonNull
    public User updateUser(@NonNull Long id, @NonNull UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundAlertException("User not found id: " + id, "user"));

        userMapper.updateFromRequest(request, user);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @NonNull
    public UserDTO getUserInfo(@NonNull String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundAlertException("User not found username: " + username, "user"));

        return userMapper.toUserDTO(user);
    }

    public Optional<User> fetchAndUpdateUserWithAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)) {
            return Optional.empty();
        }

        String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        return userRepository.findByUsername(username);
    }


}
