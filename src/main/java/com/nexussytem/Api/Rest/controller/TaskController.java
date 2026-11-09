package com.nexussytem.Api.Rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.nexussytem.Api.Rest.model.project.ProjectRepository;
import com.nexussytem.Api.Rest.model.task.Task;
import com.nexussytem.Api.Rest.model.task.TaskRepository;
import com.nexussytem.Api.Rest.model.task.DTO.DadosAtualizacaoTask;
import com.nexussytem.Api.Rest.model.task.DTO.DadosCadastroTask;
import com.nexussytem.Api.Rest.model.task.DTO.DadosListagemTask;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskController(
            TaskRepository taskRepository,
            ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/listar")
    public List<DadosListagemTask> listar() {
        return taskRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DadosListagemTask> detalhar(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(converter(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DadosListagemTask> cadastrar(@Valid @RequestBody DadosCadastroTask dados) {

        var projeto = projectRepository.findById(dados.projectId());

        if (projeto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = new Task();
        task.setProject(projeto.get());
        task.setTitle(dados.title());
        task.setDescription(dados.description());
        task.setDueDate(dados.dueDate());

        if (dados.priority() != null) {
            task.setPriority(dados.priority());
        }

        if (dados.status() != null) {
            task.setStatus(dados.status());
        }

        return ResponseEntity.ok(converter(taskRepository.save(task)));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<DadosListagemTask> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoTask dados) {

        var resultado = taskRepository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = resultado.get();

        if (dados.title() != null) {
            task.setTitle(dados.title());
        }

        if (dados.description() != null) {
            task.setDescription(dados.description());
        }

        if (dados.dueDate() != null) {
            task.setDueDate(dados.dueDate());
        }

        if (dados.status() != null) {
            task.setStatus(dados.status());
        }

        if (dados.priority() != null) {
            task.setPriority(dados.priority());
        }

        return ResponseEntity.ok(converter(taskRepository.save(task)));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Transforma a Task no DTO que vai para o JSON
    private DadosListagemTask converter(Task task) {
        return new DadosListagemTask(
                task.getId(),
                task.getProject().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }
}