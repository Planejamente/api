package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ExperienciaFormacao")
@Getter
@Setter
public class ExperienciaFormacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String instituicao;
    private String cargo;
    private String descricao;
    @ManyToOne
    private Psicologo psicologo;
    private String tipo;
    private String titulo;
}
