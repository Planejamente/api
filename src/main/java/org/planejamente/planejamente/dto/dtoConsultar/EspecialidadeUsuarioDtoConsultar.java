package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;

import java.util.UUID;

@Data
public class EspecialidadeUsuarioDtoConsultar {
    private UUID id;
    private EspecialidadeUsuarioDtoConsultar.Psicologo psicologo;
    private EspecialidadeUsuarioDtoConsultar.Especialidade especialidade;

    @Data
    public static class Psicologo {
        private UUID id;
        private String nome;
        private String email;
    }

    @Data
    public static class Especialidade {
        private UUID id;
        private String titulo;
        private String descricao;
    }
}