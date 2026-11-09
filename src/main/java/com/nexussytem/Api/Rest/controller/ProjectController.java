package com.nexussytem.Api.Rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.project.ProjectRepository;
import com.nexussytem.Api.Rest.model.project.DTO.DadosAtualizacaoProject;
import com.nexussytem.Api.Rest.model.project.DTO.DadosCadastroProject;
import com.nexussytem.Api.Rest.model.project.DTO.DadosListagemProject;
import com.nexussytem.Api.Rest.model.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectController(
            ProjectRepository projectRepository,
            UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/listar")
    public List<DadosListagemProject> listar() {
        return projectRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DadosListagemProject> detalhar(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> ResponseEntity.ok(converter(project)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Projetos de um usuário (usado pelo app depois do login)
    @GetMapping("/usuario/{userId}")
    public List<DadosListagemProject> listarPorUsuario(@PathVariable Long userId) {
        return projectRepository.findByUserId(userId)
                .stream()
                .map(this::converter)
                .toList();
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosListagemProject> cadastrar(
            @Valid 
            @RequestParam Long userId,
            @RequestBody DadosCadastroProject dados) {

        var usuario = userRepository.findById(userId);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Project project = new Project();
        project.setUser(usuario.get());
        project.setName(dados.name());
        project.setDescription(dados.description());
        project.setColor(dados.color());

        return ResponseEntity.ok(converter(projectRepository.save(project)));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<DadosListagemProject> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoProject dados) {

        var resultado = projectRepository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Project project = resultado.get();

        if (dados.name() != null) {
            project.setName(dados.name());
        }

        if (dados.description() != null) {
            project.setDescription(dados.description());
        }

        if (dados.color() != null) {
            project.setColor(dados.color());
        }

        return ResponseEntity.ok(converter(projectRepository.save(project)));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Transforma o Project no DTO que vai para o JSON
    private DadosListagemProject converter(Project project) {
        return new DadosListagemProject(
                project.getId(),
                project.getUser().getId(),
                project.getName(),
                project.getDescription(),
                project.getColor());
    }
}