package com.nexussytem.Api.Rest.model.user.DTO;

// O que a API devolve sobre o usuário (sem a senha)
public record DadosListagemUser(
        Long id,
        String name,
        String email,
        String avatarUrl) {
}