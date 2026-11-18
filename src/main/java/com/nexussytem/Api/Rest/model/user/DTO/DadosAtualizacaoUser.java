package com.nexussytem.Api.Rest.model.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

// Todos os campos são opcionais, mas se vierem precisam ser válidos
public record DadosAtualizacaoUser(

        @Size(min = 1, max = 100, message = "O nome deve ter entre 1 e 100 caracteres.")
        String name,

        @Email(message = "E-mail inválido.")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
        String email,

        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
        String password,

        String avatarUrl) {
}