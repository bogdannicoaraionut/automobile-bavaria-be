package com.automobilebavaria.backend.mapper;

import com.automobilebavaria.backend.dto.CreateUserRequest;
import com.automobilebavaria.backend.dto.UserDTO;
import com.automobilebavaria.backend.entity.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", ignore = true)
    public abstract User toUser(CreateUserRequest createUserRequest);

    @AfterMapping
    protected void customizeUser(CreateUserRequest request, @MappingTarget User user) {
        int atIndex = request.email().indexOf('@');
        user.setUsername(atIndex > 0 ? request.email().substring(0, atIndex) : request.email());
        user.setEmail(request.email());

        user.setPassword(passwordEncoder.encode(request.password()));
    }

    public abstract UserDTO toUserDTO(User user);

}
