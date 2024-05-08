package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;

import java.util.List;
import java.util.Objects;

public class ConsultaMapper {
    public static Consulta toEntity(ConsultaDto dto) {
        if(Objects.isNull(dto)) return null;

        Consulta consulta = new Consulta();

        consulta.setPaciente(dto.getPaciente());
        consulta.setPsicologo(dto.getPsicologo());
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
        dto.setPaciente(consulta.getPaciente());
        dto.setPsicologo(consulta.getPsicologo());
        dto.setLinkMeet(consulta.getLinkMeet());
        dto.setInicio(consulta.getInicio());
        dto.setFim(consulta.getFim());

        return dto;
    }

    public static List<ConsultaDtoConsultar> toDto(List<Consulta> consultas) {
        return consultas.stream().map(ConsultaMapper::toDto).toList();
    }
}