package com.nexussytem.Api.Rest.model.user;

public record DadosCadastroUser(
    String nome,
    String email,
    String senhaHash,
    String avatarUrl
) {
    
}
