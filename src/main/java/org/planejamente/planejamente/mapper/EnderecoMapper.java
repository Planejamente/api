package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
import org.planejamente.planejamente.entity.Endereco;

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
}
