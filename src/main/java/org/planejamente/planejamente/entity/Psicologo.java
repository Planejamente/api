package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Psicologo extends Usuario {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
    private String crp;
    private String cnpj;
    private String cpf;
    @OneToOne
    private Usuario usuario;
}
