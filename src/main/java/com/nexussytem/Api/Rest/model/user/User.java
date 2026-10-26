package com.nexussytem.Api.Rest.model.user;

import java.time.LocalDateTime;

import com.nexussytem.Api.Rest.model.user.DTO.DadosCadastroUser;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password_hash;

    @Column(length = 500)
    private String avatarUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    public User(DadosCadastroUser dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.password_hash = dados.password_hash();
        this.avatarUrl = dados.avatarUrl();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public void update(DadosCadastroUser dados) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.password_hash() != null) {
            this.password_hash = dados.password_hash();
        }
        if (dados.avatarUrl() != null) {
            this.avatarUrl = dados.avatarUrl();
        }
        this.updated_at = LocalDateTime.now();
    }
}
