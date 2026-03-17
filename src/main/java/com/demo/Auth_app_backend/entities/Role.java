package com.demo.Auth_app_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users_role")
public class Role {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(nullable = false,unique = true)
    private String name;
}
