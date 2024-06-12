package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EnderecoDtoConsultar {
    private UUID id;
    private String cep;
    private String rua;
    private String estado;
    private String cidade;
    private EnderecoDtoConsultar.Usuario usuario;

    @Data
    public static class Usuario {
        private UUID id;
        private String nome;
        private String email;
    }
}