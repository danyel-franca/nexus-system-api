package com.nexussytem.Api.Rest.model.task.DTO;

import java.time.LocalDate;

import com.nexussytem.Api.Rest.model.task.TaskPriority;
import com.nexussytem.Api.Rest.model.task.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroTask(

        @NotNull(message = "O ID do projeto é obrigatório.")
        Long projectId,

        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 200, message = "O título deve ter no máximo 200 caracteres.")
        String title,

        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority) {
}