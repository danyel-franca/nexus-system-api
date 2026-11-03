package com.nexussytem.Api.Rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.project.ProjectRepository;
import com.nexussytem.Api.Rest.model.task.Task;
import com.nexussytem.Api.Rest.model.task.TaskRepository;
import com.nexussytem.Api.Rest.model.task.DTO.DadosCadastroTask;
import com.nexussytem.Api.Rest.model.task.DTO.DadosListagemTask;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskController(
            TaskRepository taskRepository,
            ProjectRepository projectRepository) {

        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping("/cadastrar")
    public DadosListagemTask cadastrar(
            @RequestBody DadosCadastroTask dados) {

        if (dados.projectId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O ID do projeto é obrigatório.");
        }

        Project project = projectRepository.findById(dados.projectId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Projeto não encontrado."));

        Task task = new Task();

        task.setProject(project);
        task.setTitle(dados.title());
        task.setDescription(dados.description());

        if (dados.priority() != null) {
            task.setPriority(dados.priority());
        }

        if (dados.status() != null) {
            task.setStatus(dados.status());
        }

        task.setDueDate(dados.dueDate());

        Task tarefaSalva = taskRepository.save(task);

        return new DadosListagemTask(
                tarefaSalva.getId(),
                project.getId(),
                tarefaSalva.getTitle(),
                tarefaSalva.getDescription(),
                tarefaSalva.getDueDate(),
                tarefaSalva.getStatus(),
                tarefaSalva.getPriority(),
                tarefaSalva.getCreatedAt(),
                tarefaSalva.getUpdatedAt());
    }
}