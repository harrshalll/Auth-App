package com.demo.Auth_app_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(length = 100)
    private String name;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean isEnable = true;
    private String image;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    @Enumerated(EnumType.STRING)
    private Provider provider = Provider.LOCAL;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                  joinColumns = @JoinColumn(name = "user_id"),
                  inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //This method is for entity lifecycle
    //The @PrePersist annotation is a Java Persistence API (JPA) lifecycle callback used in Spring Boot applications to
    // specify a method that should run automatically just before an entity is first saved (inserted) into the database.
    // It is a powerful way to manage logic related to the creation of a new entity without cluttering service-layer code.
    @PrePersist
    protected void OnCreate() {
        Instant now = Instant.now();
        if (createdAt == null) {
            createdAt = now;
        }else{
            updatedAt = now;
        }
    }
    //The @PreUpdate annotation in Spring Boot is part of the JPA (Java Persistence API) entity lifecycle callbacks.
    // It is used to designate a method within an entity class that will be automatically invoked by the persistence
    // provider (like Hibernate) just before the entity's changes are synchronized with the database
    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }
}
