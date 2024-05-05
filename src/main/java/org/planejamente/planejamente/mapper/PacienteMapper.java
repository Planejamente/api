package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.PacienteDto;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.UsuarioRole;

import java.util.Objects;

public class PacienteMapper extends UsuarioMapper<Paciente, PacienteDto>{
    @Override
    public Paciente toEntity(PacienteDto dto) {
        if(Objects.isNull(dto)) return null;

        return super.toEntity(dto);
    }
}
