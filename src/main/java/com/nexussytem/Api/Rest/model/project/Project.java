package com.nexussytem.Api.Rest.model.project;

import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nexussytem.Api.Rest.model.project.DTO.DadosCadastroProject;
import com.nexussytem.Api.Rest.model.task.Task;
import com.nexussytem.Api.Rest.model.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 7)
    private String color;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Project(DadosCadastroProject dados, User user) {
        this.user_id = user;
        this.name = dados.name();
        this.description = dados.description();
        this.color = dados.color();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public void update(DadosCadastroProject dados) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.description() != null) {
            this.description = dados.description();
        }
        if (dados.color() != null) {
            this.color = dados.color();
        }
        this.updated_at = LocalDateTime.now();
    }
}
