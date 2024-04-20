package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Consulta")
@Getter
@Setter
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dataHoraInicio;
    private LocalDate dataHoraFim;
    private String status;
    private String observacoes;
    @ManyToOne
    private Psicologo psicologo;
    @ManyToOne
    private Paciente paciente;
    @CreationTimestamp
    private LocalDate dtCriacao;
}
