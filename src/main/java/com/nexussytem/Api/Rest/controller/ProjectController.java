
package com.nexussytem.Api.Rest.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.project.ProjectRepository;
import com.nexussytem.Api.Rest.model.project.DTO.DadosAtualizacaoProject;
import com.nexussytem.Api.Rest.model.project.DTO.DadosCadastroProject;
import com.nexussytem.Api.Rest.model.user.UserRepository;

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
    public List<Project> listar() {
        return projectRepository.findAll();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Project> detalhar(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrar(
            @RequestParam Long userId,
            @RequestBody DadosCadastroProject dados) {

        var usuario = userRepository.findById(userId);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LocalDateTime agora = LocalDateTime.now();

        Project project = new Project();
        project.setUser_id(usuario.get());
        project.setName(dados.name());
        project.setDescription(dados.description());
        project.setColor(dados.color());
        project.setCreated_at(agora);
        project.setUpdated_at(agora);

        return ResponseEntity.ok(projectRepository.save(project));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(
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

        return ResponseEntity.ok(projectRepository.save(project));
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
}
