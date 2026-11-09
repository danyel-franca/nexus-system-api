package com.nexussytem.Api.Rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nexussytem.Api.Rest.model.user.User;
import com.nexussytem.Api.Rest.model.user.UserRepository;
import com.nexussytem.Api.Rest.model.user.DTO.DadosAtualizacaoUser;
import com.nexussytem.Api.Rest.model.user.DTO.DadosCadastroUser;
import com.nexussytem.Api.Rest.model.user.DTO.DadosListagemUser;
import com.nexussytem.Api.Rest.model.user.DTO.DadosLogin;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    // Gera e confere o hash das senhas
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/listar")
    public List<DadosListagemUser> listar() {
        return userRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DadosListagemUser> detalhar(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(converter(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosListagemUser> cadastrar(@Valid @RequestBody DadosCadastroUser dados) {

        // E-mail já usado -> 409 Conflict
        if (userRepository.existsByEmail(dados.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User usuario = new User();
        usuario.setName(dados.name());
        usuario.setEmail(dados.email());
        usuario.setPassword_hash(encoder.encode(dados.password()));
        usuario.setAvatarUrl(dados.avatarUrl());

        return ResponseEntity.ok(converter(userRepository.save(usuario)));
    }

    @PostMapping("/login")
    public ResponseEntity<DadosListagemUser> login(@RequestBody DadosLogin dados) {

        var resultado = userRepository.findByEmail(dados.email());

        // E-mail não existe ou senha errada -> 401 Unauthorized
        if (resultado.isEmpty()
                || !encoder.matches(dados.password(), resultado.get().getPassword_hash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(converter(resultado.get()));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<DadosListagemUser> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoUser dados) {

        var resultado = userRepository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User usuario = resultado.get();

        if (dados.name() != null) {
            usuario.setName(dados.name());
        }

        if (dados.email() != null) {
            usuario.setEmail(dados.email());
        }

        if (dados.password() != null) {
            usuario.setPassword_hash(encoder.encode(dados.password()));
        }

        if (dados.avatarUrl() != null) {
            usuario.setAvatarUrl(dados.avatarUrl());
        }

        return ResponseEntity.ok(converter(userRepository.save(usuario)));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Transforma o User no DTO que vai para o JSON (sem a senha)
    private DadosListagemUser converter(User user) {
        return new DadosListagemUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatarUrl());
    }
}