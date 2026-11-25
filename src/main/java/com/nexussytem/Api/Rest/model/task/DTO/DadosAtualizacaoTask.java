package com.nexussytem.Api.Rest.model.task.DTO;

import java.time.LocalDate;

import com.nexussytem.Api.Rest.model.task.TaskPriority;
import com.nexussytem.Api.Rest.model.task.TaskStatus;

import jakarta.validation.constraints.Size;

// Todos os campos são opcionais, mas se vierem precisam ser válidos
public record DadosAtualizacaoTask(

        // Opcional: permite mover a tarefa para outro projeto
        Long projectId,

        @Size(min = 1, max = 200, message = "O título deve ter entre 1 e 200 caracteres.")
        String title,

        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority,

        // true = remove a data de vencimento (null em dueDate apenas mantém a atual)
        Boolean clearDueDate) {
}