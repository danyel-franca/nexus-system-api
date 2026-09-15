package com.nexussytem.Api.Rest.model.task;

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
}
