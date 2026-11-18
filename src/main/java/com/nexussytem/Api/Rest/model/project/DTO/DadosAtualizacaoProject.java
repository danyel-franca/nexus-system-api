package com.nexussytem.Api.Rest.model.project.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Todos os campos são opcionais, mas se vierem precisam ser válidos
public record DadosAtualizacaoProject(

        @Size(min = 1, max = 150, message = "O nome deve ter entre 1 e 150 caracteres.")
        String name,

        String description,

        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "A cor deve estar no formato #RRGGBB.")
        String color) {
}