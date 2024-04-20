package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
public abstract class Usuario {
    @Id
    @UuidGenerator
    private UUID id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String telefone;
    private String genero;
    @OneToOne
    private Acesso acesso;
}