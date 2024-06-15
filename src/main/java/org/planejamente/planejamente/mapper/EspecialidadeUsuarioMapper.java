package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeUsuarioDtoConsultar;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.EspecialidadeUsuario;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;
import java.util.Objects;

public class EspecialidadeUsuarioMapper {
    public static EspecialidadeUsuarioDtoConsultar toDto(EspecialidadeUsuario entidade) {
        if(Objects.isNull(entidade)) return null;

        EspecialidadeUsuarioDtoConsultar dto = new EspecialidadeUsuarioDtoConsultar();
        dto.setId(entidade.getId());

        Psicologo psiEntidade = entidade.getPsicologo();
        EspecialidadeUsuarioDtoConsultar.Psicologo psiDto = new EspecialidadeUsuarioDtoConsultar.Psicologo();

        if(!Objects.isNull(psiEntidade)) {
            psiDto.setId(psiEntidade.getId());
            psiDto.setNome(psiEntidade.getNome());
            psiDto.setEmail(psiEntidade.getEmail());
        }

        dto.setPsicologo(psiDto);

        Especialidade espEntidade = entidade.getEspecialidade();
        EspecialidadeUsuarioDtoConsultar.Especialidade espDto = new EspecialidadeUsuarioDtoConsultar.Especialidade();

        if(!Objects.isNull(espEntidade)) {
            espDto.setId(espEntidade.getId());
            espDto.setTitulo(espEntidade.getTitulo());
            espDto.setDescricao(espEntidade.getDescricao());
        }

        dto.setEspecialidade(espDto);
        return dto;
    }

    public static List<EspecialidadeUsuarioDtoConsultar> toDto(List<EspecialidadeUsuario> lista) {
        return lista.stream().map(EspecialidadeUsuarioMapper::toDto).toList();
    }
}