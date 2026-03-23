package com.demo.Auth_app_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "refresh_token",indexes = {//Indexing for making fetching fast, because we fetch refresh token frequently
        @Index(name = "refresh_token_jti_idx",columnList = "jti", unique = true),
        @Index(name = "refresh_token_user_id_idx",columnList = "user_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID refreshTokenId;

    @Column(name = "jti",updatable = false,nullable = false)
    private String jti;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false,updatable = false)
    private User user;

    @Column(nullable = false,updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiredAt;

    @Column(nullable = false)
    private boolean isRevoked;

    private String replacedByToken;

}
