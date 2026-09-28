package com.nexussytem.Api.Rest.model.task;

public record DadosAtualizacaoTask(
        String titulo,
        String descricao,
        String dataEntrega,
        TaskStatus status,
        TaskPriority prioridade) {

}
