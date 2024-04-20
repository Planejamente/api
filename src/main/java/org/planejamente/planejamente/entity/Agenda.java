package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Agenda")
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    @OneToOne
    private Psicologo psicologo;
}