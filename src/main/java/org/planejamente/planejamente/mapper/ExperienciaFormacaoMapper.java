package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.ExperienciaFormacaoDto;
import org.planejamente.planejamente.entity.ExperienciaFormacao;

import java.util.Objects;

public class ExperienciaFormacaoMapper {
    public static ExperienciaFormacao toEntity(ExperienciaFormacaoDto dto) {
        if(Objects.isNull(dto)) return null;

        ExperienciaFormacao experienciaFormacao = new ExperienciaFormacao();

        experienciaFormacao.setDataInicio(dto.getDataInicio());
        experienciaFormacao.setDataFim(dto.getDataFim());
        experienciaFormacao.setInstituicao(dto.getInstituicao());
        experienciaFormacao.setCargo(dto.getCargo());
        experienciaFormacao.setDescricao(dto.getDescricao());
        experienciaFormacao.setTipo(dto.getTipo());
        experienciaFormacao.setTitulo(dto.getTitulo());
        experienciaFormacao.setPsicologo(dto.getPsicologo());

        return experienciaFormacao;
    }
}
