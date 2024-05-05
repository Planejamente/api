package org.planejamente.planejamente.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.planejamente.planejamente.entity.usuario.Usuario;

import java.util.UUID;

@Entity
@Table(name = "Endereco")
@Getter
@Setter
public class Endereco {
    @Id
        @UuidGenerator
    private UUID id;
    private String cep;
    private String rua;
    private String estado;
    private String cidade;
    @OneToOne
    private Usuario usuario;
}