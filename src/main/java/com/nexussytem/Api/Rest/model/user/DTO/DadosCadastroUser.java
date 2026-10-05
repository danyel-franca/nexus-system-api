package com.nexussytem.Api.Rest.model.user.DTO;

public record DadosCadastroUser(
    String name,
    String email,
    String password_hash,
    String avatarUrl
) {
    
}
