package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ExperienciaFormacao")
@Getter
@Setter
public class ExperienciaFormacao {
    @Id
    @UuidGenerator
    private UUID id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String instituicao;
    private String cargo;
    private String descricao;
    private String tipo;
    private String titulo;
    @ManyToOne
    private Psicologo psicologo;
}