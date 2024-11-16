package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoTarefasDto;
import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;

import java.util.Objects;

public class PlanoDeAcaoTarefasMapper {
    public static PlanoDeAcaoTarefas toEntity(PlanoDeAcaoTarefasDto dto) {
        if(Objects.isNull(dto)) return null;

        PlanoDeAcaoTarefas pdat = new PlanoDeAcaoTarefas();

        pdat.setIdTarefa(dto.getIdTarefa());
        pdat.setPredecessor(dto.getPredecessor());
        pdat.setTarefa(dto.getTarefa());
        pdat.setStatus(dto.getStatus());

        return pdat;
    }

    public static PlanoDeAcaoTarefas merge(PlanoDeAcaoTarefas antiga, PlanoDeAcaoTarefas atualizada) {
        if(Objects.isNull(atualizada)) return antiga;

        antiga.setIdTarefa(Objects.isNull(atualizada.getIdTarefa()) ? antiga.getIdTarefa() : atualizada.getIdTarefa());
        antiga.setPredecessor(Objects.isNull(atualizada.getPredecessor()) ? antiga.getPredecessor() : atualizada.getPredecessor());
        antiga.setIdTarefa(Objects.isNull(atualizada.getIdTarefa()) ? antiga.getIdTarefa() : atualizada.getIdTarefa());
        antiga.setIdTarefa(Objects.isNull(atualizada.getIdTarefa()) ? antiga.getIdTarefa() : atualizada.getIdTarefa());

        return antiga;
    }
}
