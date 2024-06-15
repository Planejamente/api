package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeDto;
import org.planejamente.planejamente.entity.Especialidade;

import java.util.List;
import java.util.Objects;

public class EspecialidadeMapper {
    public static Especialidade toEntity(EspecialidadeDto dto) {
        if(Objects.isNull(dto)) return null;

        Especialidade especialidade = new Especialidade();

        especialidade.setDescricao(dto.getDescricao());
        especialidade.setTitulo(dto.getTitulo());

        return especialidade;
    }

    public static EspecialidadeDtoConsultar toDto(Especialidade especialidade) {
        if(Objects.isNull(especialidade)) return null;

        EspecialidadeDtoConsultar dto = new EspecialidadeDtoConsultar();
        dto.setId(especialidade.getId());
        dto.setDescricao(especialidade.getDescricao());
        dto.setTitulo(especialidade.getTitulo());

        return dto;
    }

    public static List<EspecialidadeDtoConsultar> toDto(List<Especialidade> especialidades) {
        return especialidades.stream().map(EspecialidadeMapper::toDto).toList();
    }
}
