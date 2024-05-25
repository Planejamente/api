package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Consulta")
@Getter
@Setter
public class Consulta {
    @Id
        @UuidGenerator
    private UUID id;
    private String avaliacao;
    private Double nota;
    private LocalDateTime dtCriacao;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Psicologo psicologo;
    private String linkMeet;
    private String linkAnamnese;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String idAnamnese;
}