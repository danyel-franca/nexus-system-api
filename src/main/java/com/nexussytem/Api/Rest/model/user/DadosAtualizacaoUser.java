package com.nexussytem.Api.Rest.model.user;

public record DadosAtualizacaoUser(
        String nome,
        String email,
        String senhaHash,
        String avatarUrl) {

}
