package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;

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
}