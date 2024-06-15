package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.UUID;

@Entity
@Table(name = "EspecialidadeUsuario")
@Getter
@Setter
public class EspecialidadeUsuario {
    @Id
    @UuidGenerator
    private UUID id;
    @ManyToOne
    private Psicologo psicologo;
    @ManyToOne
    private Especialidade especialidade;
}