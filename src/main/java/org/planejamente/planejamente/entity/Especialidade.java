package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "Especialidade")
@Getter
@Setter
public class Especialidade {
    @Id
        @UuidGenerator
    private UUID id;
    private String descricao;
    private String titulo;
}
