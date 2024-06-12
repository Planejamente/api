package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.UUID;

@Entity
@Table(name = "Especialidade")
@Getter
@Setter
public class Especialidade {
    @Id
        @UuidGenerator
    private UUID id;
    private String descricao;
    private String titulo;
    @ManyToOne
    private Psicologo psicologo;
}