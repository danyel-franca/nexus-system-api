package com.nexussytem.Api.Rest.model.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    // Todas as tarefas de todos os projetos de um usuário
    List<Task> findByProjectUserId(Long userId);
}