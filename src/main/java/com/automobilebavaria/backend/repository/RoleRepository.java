package com.automobilebavaria.backend.repository;

import com.automobilebavaria.backend.entity.Role;
import com.automobilebavaria.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
