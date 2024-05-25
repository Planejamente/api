package org.planejamente.planejamente.mapper;

import lombok.Data;
import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ConsultaMapper {
    public static Consulta toEntity(ConsultaDto dto) {
        if(Objects.isNull(dto)) return null;

        Consulta consulta = new Consulta();

        consulta.setDtCriacao(dto.getDtCriacao());
        consulta.setLinkMeet(dto.getLinkMeet());
        consulta.setLinkAnamnese(dto.getLinkAnamnese());
        consulta.setInicio(dto.getInicio());
        consulta.setFim(dto.getFim());
        consulta.setIdAnamnese(dto.getIdAnamnese());

        return consulta;
    }

    public static ConsultaDtoConsultar toDto(Consulta consulta) {
        if(Objects.isNull(consulta)) return null;

        ConsultaDtoConsultar dto = new ConsultaDtoConsultar();

        dto.setId(consulta.getId());
        dto.setLinkMeet(consulta.getLinkMeet());
        dto.setInicio(consulta.getInicio());
        dto.setFim(consulta.getFim());

        Paciente pacienteEntidade = consulta.getPaciente();
        ConsultaDtoConsultar.PacienteDtoConsultar pacienteDto = new ConsultaDtoConsultar.PacienteDtoConsultar();

        pacienteDto.setId(pacienteEntidade.getId());
        pacienteDto.setNome(pacienteEntidade.getNome());
        pacienteDto.setTelefone(pacienteEntidade.getTelefone());
        pacienteDto.setGenero(pacienteEntidade.getGenero());
        pacienteDto.setEmail(pacienteEntidade.getEmail());

        dto.setPaciente(pacienteDto);

        Psicologo psicologoEntidade = consulta.getPsicologo();
        ConsultaDtoConsultar.PsicologoDtoConsultar psicologoDto = new ConsultaDtoConsultar.PsicologoDtoConsultar();

        psicologoDto.setId(psicologoEntidade.getId());
        psicologoDto.setNome(psicologoEntidade.getNome());
        psicologoDto.setTelefone(psicologoEntidade.getTelefone());
        psicologoDto.setGenero(psicologoEntidade.getGenero());
        psicologoDto.setEmail(psicologoEntidade.getEmail());

        dto.setPsicologo(psicologoDto);

        return dto;
    }

    public static List<ConsultaDtoConsultar> toDto(List<Consulta> consultas) {
        return consultas.stream().map(ConsultaMapper::toDto).toList();
    }
}