package com.nexussytem.Api.Rest.model.task.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nexussytem.Api.Rest.model.task.TaskPriority;
import com.nexussytem.Api.Rest.model.task.TaskStatus;

public record DadosListagemTask(
        Long id,
        Long projectId,
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
