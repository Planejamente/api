package org.planejamente.planejamente.dto.dtoConsultar;

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
    private Paciente paciente;
    private Psicologo psicologo;
    private String linkMeet;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}