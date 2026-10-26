package com.nexussytem.Api.Rest.model.project.DTO;

import com.nexussytem.Api.Rest.model.project.Project;

public record DadosListagemProject(
        Long id,
        String name,
        String description,
        String color
) {

}
