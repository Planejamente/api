package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "EspecialidadePsicologo")
@Getter
@Setter
public class EspecialidadePsicologo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Especialidade especialidade;
    @ManyToOne
    private Psicologo psicologo;
}
