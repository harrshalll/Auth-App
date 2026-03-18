package com.demo.Auth_app_backend.dtos;

import com.demo.Auth_app_backend.entities.Provider;
import com.demo.Auth_app_backend.entities.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
public class UserDto {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private boolean isEnable = true;
    private String image;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private Provider provider = Provider.LOCAL;
    private Set<Role> roles = new HashSet<>();
}
