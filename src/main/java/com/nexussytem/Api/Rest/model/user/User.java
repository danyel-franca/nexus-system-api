package com.nexussytem.Api.Rest.model.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class User {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senhaHash;
    private String avatarUrl;
    private String criadoEm;
    private String alteradoEm;
}
