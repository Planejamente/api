package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class UsuarioDtoConsultar {
    private UUID id;
    private String nome;
    private String telefone;
    private String genero;
    private String email;
}