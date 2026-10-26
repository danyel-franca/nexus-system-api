
package com.nexussytem.Api.Rest.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nexussytem.Api.Rest.model.user.User;
import com.nexussytem.Api.Rest.model.user.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/listar")
    public List<User> listar() {
        return userRepository.findAll();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<User> detalhar(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<User> cadastrar(@RequestBody User dados) {
        User usuario = new User();

        usuario.setName(dados.getName());
        usuario.setEmail(dados.getEmail());
        usuario.setPassword_hash(dados.getPassword_hash());
        usuario.setAvatarUrl(dados.getAvatarUrl());

        usuario.setCreated_at(LocalDateTime.now());
        usuario.setUpdated_at(LocalDateTime.now());

        return ResponseEntity.ok(userRepository.save(usuario));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody User dados) {

        var resultado = userRepository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User usuario = resultado.get();

        if (dados.getName() != null) {
            usuario.setName(dados.getName());
        }

        if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }

        if (dados.getPassword_hash() != null) {
            usuario.setPassword_hash(dados.getPassword_hash());
        }

        if (dados.getAvatarUrl() != null) {
            usuario.setAvatarUrl(dados.getAvatarUrl());
        }

        return ResponseEntity.ok(userRepository.save(usuario));
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
}
