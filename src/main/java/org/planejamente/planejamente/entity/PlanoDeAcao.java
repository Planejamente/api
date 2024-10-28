package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PlanoDeAcao")
@Getter
@Setter
public class PlanoDeAcao {
    @Id
    @UuidGenerator
    private UUID id;
    private String tituloPlanoDeAcao;
    @OneToMany
    private List<PlanoDeAcaoTarefas> tarefas;
    @ManyToOne
    private Consulta consulta;
}
