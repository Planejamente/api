package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.ExperienciaFormacaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ExperienciaFormacaoDto;
import org.planejamente.planejamente.entity.ExperienciaFormacao;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;
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

        return experienciaFormacao;
    }

    public static ExperienciaFormacaoDtoConsultar toDto(ExperienciaFormacao expForm) {
        if(Objects.isNull(expForm)) return null;

        ExperienciaFormacaoDtoConsultar dto = new ExperienciaFormacaoDtoConsultar();

        dto.setId(expForm.getId());
        dto.setDataInicio(expForm.getDataInicio());
        dto.setDataFim(expForm.getDataFim());
        dto.setInstituicao(expForm.getInstituicao());
        dto.setCargo(expForm.getCargo());
        dto.setDescricao(expForm.getDescricao());
        dto.setTipo(expForm.getTipo());
        dto.setTitulo(expForm.getTitulo());

        Psicologo psicologoEntidade = expForm.getPsicologo();
        ExperienciaFormacaoDtoConsultar.Psicologo psicologoDto = new ExperienciaFormacaoDtoConsultar.Psicologo();

        if(!Objects.isNull(psicologoEntidade)) {
            psicologoDto.setId(psicologoEntidade.getId());
            psicologoDto.setNome(psicologoEntidade.getNome());
            psicologoDto.setEmail(psicologoEntidade.getEmail());
        }

        dto.setPsicologo(psicologoDto);
        return dto;
    }

    public static List<ExperienciaFormacaoDtoConsultar> toDto(List<ExperienciaFormacao> expsForm) {
        return expsForm.stream().map(ExperienciaFormacaoMapper::toDto).toList();
    }
}