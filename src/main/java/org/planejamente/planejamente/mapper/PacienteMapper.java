package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.PacienteDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PacienteDto;
import org.planejamente.planejamente.entity.usuario.Paciente;

import java.util.List;
import java.util.Objects;

public class PacienteMapper extends UsuarioMapper<Paciente, PacienteDto>{
    @Override
    public Paciente toEntity(PacienteDto dto) {
        if(Objects.isNull(dto)) return null;

        return super.toEntity(dto);
    }

    public static PacienteDtoConsultar toDto(Paciente paciente) {
        if(Objects.isNull(paciente)) return null;

        PacienteDtoConsultar dto = new PacienteDtoConsultar();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setTelefone(paciente.getTelefone());
        dto.setGenero(paciente.getGenero());
        dto.setEmail(paciente.getEmail());

        return dto;
    }

    public static List<PacienteDtoConsultar> toDto(List<Paciente> pacientes) {
        return pacientes.stream().map(PacienteMapper::toDto).toList();
    }
}
