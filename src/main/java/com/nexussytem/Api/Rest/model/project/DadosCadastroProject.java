package com.nexussytem.Api.Rest.model.project;

import java.time.LocalDateTime;

import com.nexussytem.Api.Rest.model.task.DadosCadastroTask;

public record DadosCadastroProject(
        String nome,
        String descricao,
        String color,
        LocalDateTime criadoEm,
        LocalDateTime alteradoEm,
        DadosCadastroTask task) {

}
