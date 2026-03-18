package com.demo.Auth_app_backend.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;
@Data
public class RoleDto {
    private UUID id = UUID.randomUUID();
    private String name;
}
