package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EspecialidadeDtoConsultar {
    private UUID id;
    private String descricao;
    private String titulo;
}
