package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlanoDeAcaoDtoConsultar {
    private UUID id;
    private String tituloPlanoDeAcao;
    private List<PlanoDeAcaoDtoConsultar.Tarefa> tarefas;
    private PlanoDeAcaoDtoConsultar.Consulta consulta;

    @Data
    public static class Tarefa {
        private UUID id;
        private String idTarefa;
        private String predecessor;
        private String tarefa;
        private String status;
    }

    @Data
    public static class Consulta {
        private UUID id;
        private UUID psicologo;
        private UUID paciente;
    }
}
