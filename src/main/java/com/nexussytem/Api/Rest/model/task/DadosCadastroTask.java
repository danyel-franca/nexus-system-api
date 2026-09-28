package com.nexussytem.Api.Rest.model.task;

import com.nexussytem.Api.Rest.model.project.DadosCadastroProject;

public record DadosCadastroTask(
        String titulo,
        String descricao,
        String dataEntrega,
        String criadoEm,
        String alteradoEm,
        TaskStatus status,
        TaskPriority prioridade,
        DadosCadastroProject project
    ) {

}
