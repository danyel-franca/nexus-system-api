package com.nexussytem.Api.Rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.project.DTO.DadosAtualizacaoProject;
import com.nexussytem.Api.Rest.model.project.DTO.DadosCadastroProject;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("project")
public class ProjectController {

    @GetMapping("/listar")
    public ResponseEntity<List<Project>> listar() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Project> detalhar(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    @Transactional 
    public ResponseEntity<Project> cadastrar(
            @RequestBody DadosCadastroProject dados) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar/{id}")
    @Transactional 
    public ResponseEntity<Project> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoProject dados) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional 
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
