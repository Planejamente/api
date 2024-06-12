package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Endereco;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PsicologoDtoConsultar extends UsuarioDtoConsultar {
    private String idCalendarioDisponivel;
    private String idCalendarioConsulta;
    private String descricao;
}