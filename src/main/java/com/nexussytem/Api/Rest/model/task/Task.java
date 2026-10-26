package com.nexussytem.Api.Rest.model.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.task.DTO.DadosCadastroTask;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskPriority priority = TaskPriority.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.TODO;

    private LocalDate dueDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Task(DadosCadastroTask dados, Project project) {
        this.project = project;
        this.title = dados.title();
        this.description = dados.description();
        this.priority = dados.priority();
        this.status = dados.status();
        this.dueDate = dados.dueDate();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(DadosCadastroTask dados) {
        if (dados.title() != null) {
            this.title = dados.title();
        }
        if (dados.description() != null) {
            this.description = dados.description();
        }
        if (dados.priority() != null) {
            this.priority = dados.priority();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.dueDate() != null) {
            this.dueDate = dados.dueDate();
        }
        this.updatedAt = LocalDateTime.now();
    }
}