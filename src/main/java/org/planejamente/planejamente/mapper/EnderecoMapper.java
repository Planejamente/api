package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
import org.planejamente.planejamente.entity.Endereco;
import org.planejamente.planejamente.entity.usuario.Usuario;

import java.util.List;
import java.util.Objects;

public class EnderecoMapper {
    public static Endereco toEntity(EnderecoDto dto) {
        if(Objects.isNull(dto)) return null;

        Endereco endereco = new Endereco();

        endereco.setCep(dto.getCep());
        endereco.setRua(dto.getRua());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());

        return endereco;
    }

    public static EnderecoDtoConsultar toDto(Endereco endereco) {
        if(Objects.isNull(endereco)) return null;

        EnderecoDtoConsultar dto = new EnderecoDtoConsultar();

        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setRua(endereco.getRua());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());

        EnderecoDtoConsultar.Usuario dtoUsuario = new EnderecoDtoConsultar.Usuario();
        Usuario usuarioEntidade = endereco.getUsuario();

        if(!Objects.isNull(usuarioEntidade)) {
            dtoUsuario.setId(usuarioEntidade.getId());
            dtoUsuario.setNome(usuarioEntidade.getNome());
            dtoUsuario.setEmail(usuarioEntidade.getEmail());
        }

        dto.setUsuario(dtoUsuario);

        return dto;
    }

    public static List<EnderecoDtoConsultar> toDto(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoMapper::toDto).toList();
    }
}