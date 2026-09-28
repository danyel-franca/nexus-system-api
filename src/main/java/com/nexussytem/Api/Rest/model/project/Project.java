package com.nexussytem.Api.Rest.model.project;

import lombok.*;
import java.time.LocalDateTime;

import com.nexussytem.Api.Rest.model.task.Task;

import jakarta.persistence.*;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer usuarioId;
    private String nome;
    private String descricao;
    private String color;
    private LocalDateTime criadoEm;
    private LocalDateTime alteradoEm;

    @Embedded
    private Task task;

    public Project(DadosCadastroProject dados){
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.color = dados.color();
        this.criadoEm = dados.criadoEm();
        this.alteradoEm = dados.alteradoEm();
        this.task = new Task(dados.task());
    }

    
}
