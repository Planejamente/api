package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Arquivos")
@Getter
@Setter
public class Arquivos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String url;
    @ManyToOne
    private Consulta consulta;
}
