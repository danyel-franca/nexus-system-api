package com.nexussytem.Api.Rest.model.project.DTO;

public record DadosListagemProject(
        Long id,
        Long userId,
        String name,
        String description,
        String color) {
}