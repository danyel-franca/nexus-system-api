package com.nexussytem.Api.Rest.model.user;

public record DadosAtualizacaoUser(
        Integer id,
        String nome,
        String email,
        String senhaHash,
        String avatarUrl) {

}
