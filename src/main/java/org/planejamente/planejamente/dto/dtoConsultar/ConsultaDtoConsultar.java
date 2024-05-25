package org.planejamente.planejamente.dto.dtoConsultar;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultaDtoConsultar {
    private UUID id;
    private ConsultaDtoConsultar.PacienteDtoConsultar paciente;
    private ConsultaDtoConsultar.PsicologoDtoConsultar psicologo;
    private String linkMeet;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @Data
    public static class PacienteDtoConsultar {
        private UUID id;
        private String nome;
        private String telefone;
        private String genero;
        private String email;
    }

    @Data
    public static class PsicologoDtoConsultar {
        private UUID id;
        private String nome;
        private String telefone;
        private String genero;
        private String email;
    }
}