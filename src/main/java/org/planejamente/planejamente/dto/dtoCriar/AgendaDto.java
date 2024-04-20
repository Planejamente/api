package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Psicologo;

import java.time.LocalTime;
@Getter
@Setter
public class AgendaDto {
    @NotBlank
        @Size(max = 255)
    private String dia;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFim;
    @OneToOne
        @NotNull
    private Psicologo psicologo;
}
