package com.nexussytem.Api.Rest.model.task;

import com.nexussytem.Api.Rest.model.project.Project;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer projetoId;
    private String titulo;
    private String descricao;
    private String dataEntrega;
    private String criadoEm;
    private String alteradoEm;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority prioridade;

    @Embedded 
    private Project project;

    public Task(DadosCadastroTask dados){
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.dataEntrega = dados.dataEntrega();
        this.criadoEm = dados.criadoEm();
        this.alteradoEm = dados.alteradoEm();
    }

    public void atualizarInformacoes(DadosAtualizacaoTask dados){
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.dataEntrega() != null){
            this.dataEntrega = dados.dataEntrega();
        }
        if(dados.status() != null){
            this.status = dados.status();
        }
        if(dados.prioridade() != null){
            this.prioridade = dados.prioridade();
        }
        
    }
}