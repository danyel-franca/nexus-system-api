package com.nexussytem.Api.Rest.model.user.DTO;

public record DadosAtualizacaoUser(
        Long id,
        String name,
        String email,
        String password,
        String avatarUrl) {
}
