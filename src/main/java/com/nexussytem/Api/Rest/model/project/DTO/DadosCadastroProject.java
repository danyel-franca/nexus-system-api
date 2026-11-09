package com.nexussytem.Api.Rest.model.project.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosCadastroProject(

        @NotBlank(message = "O nome do projeto é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
        String name,

        String description,

        // Opcional, mas se vier tem que ser no formato #RRGGBB (ex: #FF5733)
        @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "A cor deve estar no formato #RRGGBB.")
        String color) {
}