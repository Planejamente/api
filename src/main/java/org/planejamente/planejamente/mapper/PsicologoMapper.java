package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.util.List;
import java.util.Objects;

public class PsicologoMapper extends UsuarioMapper<Psicologo, PsicologoDto> {

    @Override
    public Psicologo toEntity(PsicologoDto dto) {
        if(Objects.isNull(dto)) return null;

        Psicologo psicologo = super.toEntity(dto);
        psicologo.setCrp(dto.getCrp());
        psicologo.setCnpj(dto.getCnpj());
        psicologo.setCpf(dto.getCpf());
        psicologo.setLinkFotoPerfil(dto.getLinkFotoPerfil());
        psicologo.setIdCalendarioHorarioDeTrabalho(dto.getIdCalendarioDisponivel());
        psicologo.setIdCalendarioConsulta(dto.getIdCalendarioConsulta());
        psicologo.setLinkAnamnese(dto.getLinkAnamnese());
        psicologo.setIdAnamnese(dto.getIdAnamnese());
        psicologo.setLinkFotoDeFundo(dto.getLinkFotoDeFundo());
        psicologo.setEndereco(dto.getEndereco());

        return psicologo;
    }

    public static PsicologoDtoConsultar toDto(Psicologo psicologo) {
        if(Objects.isNull(psicologo)) return null;

        PsicologoDtoConsultar dto = new PsicologoDtoConsultar();
        dto.setId(psicologo.getId());
        dto.setNome(psicologo.getNome());
        dto.setTelefone(psicologo.getTelefone());
        dto.setGenero(psicologo.getGenero());
        dto.setEmail(psicologo.getEmail());
        dto.setIdCalendarioDisponivel(psicologo.getIdCalendarioHorarioDeTrabalho());
        dto.setIdCalendarioConsulta(psicologo.getIdCalendarioConsulta());

        return dto;
    }

    public static List<PsicologoDtoConsultar> toDto(List<Psicologo> psicologos) {
        return psicologos.stream().map(PsicologoMapper::toDto).toList();
    }
}