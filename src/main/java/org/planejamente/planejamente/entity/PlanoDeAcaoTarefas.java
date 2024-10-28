package org.planejamente.planejamente.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "PlanoDeAcaoTarefas")
@Getter
@Setter
public class PlanoDeAcaoTarefas {
    @Id
    @UuidGenerator
    private UUID id;
    private String idTarefa;
    private String predecessor;
    private String tarefa;
    private String status;
    @ManyToOne
    private PlanoDeAcao planoDeAcao;
}