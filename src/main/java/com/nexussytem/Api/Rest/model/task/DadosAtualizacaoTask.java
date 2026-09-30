package com.nexussytem.Api.Rest.model.task;

public record DadosAtualizacaoTask(
        Integer id,
        String titulo,
        String descricao,
        String dataEntrega,
        TaskStatus status,
        TaskPriority prioridade) {

}
