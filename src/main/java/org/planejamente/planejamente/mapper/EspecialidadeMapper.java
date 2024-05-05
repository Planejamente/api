package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeDto;
import org.planejamente.planejamente.entity.Especialidade;

import java.util.Objects;

public class EspecialidadeMapper {
    public static Especialidade toEntity(EspecialidadeDto dto) {
        if(Objects.isNull(dto)) return null;

        Especialidade especialidade = new Especialidade();

        especialidade.setDescricao(dto.getDescricao());
        especialidade.setTitulo(dto.getTitulo());

        return especialidade;
    }
}
