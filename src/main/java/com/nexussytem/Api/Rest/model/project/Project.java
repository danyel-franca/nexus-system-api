package com.nexussytem.Api.Rest.model.project;

import lombok.*;
import java.time.LocalDateTime;
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
}
