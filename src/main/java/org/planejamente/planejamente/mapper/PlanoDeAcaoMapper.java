package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoDto;
import org.planejamente.planejamente.entity.PlanoDeAcao;

import java.util.Objects;

public class PlanoDeAcaoMapper {
    public static PlanoDeAcao toEntity(PlanoDeAcaoDto dto) {
        if(Objects.isNull(dto)) return null;

        PlanoDeAcao pda = new PlanoDeAcao();

        pda.setTituloPlanoDeAcao(dto.getTituloPlanoDeAcao());

        return pda;
    }
}
