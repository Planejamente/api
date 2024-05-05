package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.usuario.Psicologo;

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
    private Psicologo psicologo;
}