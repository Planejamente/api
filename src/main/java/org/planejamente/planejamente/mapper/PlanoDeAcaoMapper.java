package org.planejamente.planejamente.mapper;

import org.planejamente.planejamente.dto.dtoConsultar.PlanoDeAcaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.PlanoDeAcao;
import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlanoDeAcaoMapper {
    public static PlanoDeAcao toEntity(PlanoDeAcaoDto dto) {
        if(Objects.isNull(dto)) return null;

        PlanoDeAcao pda = new PlanoDeAcao();

        pda.setTituloPlanoDeAcao(dto.getTituloPlanoDeAcao());

        return pda;
    }

    public static PlanoDeAcaoDtoConsultar toDto(PlanoDeAcao pda) {
        if(Objects.isNull(pda)) return null;

        PlanoDeAcaoDtoConsultar dto = new PlanoDeAcaoDtoConsultar();
        dto.setId(pda.getId());
        dto.setTituloPlanoDeAcao(pda.getTituloPlanoDeAcao());

        List<PlanoDeAcaoTarefas> tarefasEntity = pda.getTarefas();
        List<PlanoDeAcaoDtoConsultar.Tarefa> tarefasDto = new ArrayList<PlanoDeAcaoDtoConsultar.Tarefa>();

        tarefasEntity.forEach(tarefa -> {
            PlanoDeAcaoDtoConsultar.Tarefa tarefaDto = new PlanoDeAcaoDtoConsultar.Tarefa();
            tarefaDto.setId(tarefa.getId());
            tarefaDto.setIdTarefa(tarefa.getIdTarefa());
            tarefaDto.setPredecessor(tarefa.getPredecessor());
            tarefaDto.setTarefa(tarefa.getTarefa());
            tarefaDto.setStatus(tarefa.getStatus());
            tarefasDto.add(tarefaDto);
        });

        dto.setTarefas(tarefasDto);

        Consulta consultaEntity = pda.getConsulta();
        PlanoDeAcaoDtoConsultar.Consulta consultaDto = new PlanoDeAcaoDtoConsultar.Consulta();

        if(!Objects.isNull(consultaEntity)) {
            consultaDto.setId(consultaEntity.getId());
            consultaDto.setPsicologo(consultaEntity.getPsicologo().getId());
            consultaDto.setPaciente(consultaEntity.getPaciente().getId());
        }

        dto.setConsulta(consultaDto);
        return dto;
    }

    public static List<PlanoDeAcaoDtoConsultar> toDto(List<PlanoDeAcao> lista) {
        return lista.stream().map(PlanoDeAcaoMapper::toDto).toList();
    }
}
