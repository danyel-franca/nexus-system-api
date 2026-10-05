package com.nexussytem.Api.Rest.model.task.DTO;

import java.time.LocalDate;
import com.nexussytem.Api.Rest.model.task.TaskPriority;
import com.nexussytem.Api.Rest.model.task.TaskStatus;

public record DadosAtualizacaoTask(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority) {

}
