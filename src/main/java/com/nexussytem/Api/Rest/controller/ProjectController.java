package com.nexussytem.Api.Rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexussytem.Api.Rest.model.project.DadosAtualizacaoProject;
import com.nexussytem.Api.Rest.model.project.DadosCadastroProject;
import com.nexussytem.Api.Rest.model.project.Project;
import com.nexussytem.Api.Rest.model.project.ProjectRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // POST
    @PostMapping("/cadastro")
    @Transactional
    public void cadastrarProject(@RequestBody DadosCadastroProject dados) {
        projectRepository.save(new Project(dados));
    }

    // GET
    @GetMapping("/listartodos")
    public List<Project> listarProject(@RequestParam DadosCadastroProject dados) {
        return projectRepository.findAll();
    }

    // DEL - Exclusão real
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void excluirProject(@PathVariable Integer id) {
        projectRepository.deleteById(id);
    }

    // PUT
    //@PutMapping("/atualizar")
    //@Transactional
    //public void atualizar(@RequestBody DadosAtualizacaoProject dados) {
    //    var project = projectRepository.getReferenceById(dados.id());
    //    project.atualizarInformacoes(dados);
    //}
}
