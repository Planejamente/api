package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.dto.dtoCriar.UsuarioDto;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.entity.usuario.Usuario;

@SuppressWarnings("unchecked")
public abstract class UsuarioMapper<entityT extends Usuario, dtoT extends UsuarioDto> {
    public entityT toEntity(dtoT dto) {
        Usuario entity;

        if(dto instanceof PsicologoDto) {
            entity = new Psicologo();
        } else {
            entity = new Paciente();
        }

        entity.setNome(dto.getNome());
        entity.setDataDeNascimento(dto.getDataDeNascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setGenero(dto.getGenero());
        entity.setEmail(dto.getEmail());
        entity.setGoogleSub(dto.getGoogleSub());
        entity.setEndereco(dto.getEndereco());
        entity.setRole(dto.getRole());

        return (entityT) entity;
    }
}