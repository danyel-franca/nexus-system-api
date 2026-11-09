package com.nexussytem.Api.Rest.model.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(

        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String password) {
}