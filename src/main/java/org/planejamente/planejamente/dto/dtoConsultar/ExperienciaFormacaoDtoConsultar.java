package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ExperienciaFormacaoDtoConsultar {
    private UUID id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String instituicao;
    private String cargo;
    private String descricao;
    private String tipo;
    private String titulo;
    private ExperienciaFormacaoDtoConsultar.Psicologo psicologo;

    @Data
    public static class Psicologo {
        private UUID id;
        private String nome;
        private String email;
    }
}