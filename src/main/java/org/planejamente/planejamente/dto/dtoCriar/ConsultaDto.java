package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Paciente;
import org.planejamente.planejamente.entity.Psicologo;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultaDto {
    @NotNull
        @FutureOrPresent
    private LocalDate dataHoraInicio;
    @NotNull
        @FutureOrPresent
    private LocalDate dataHoraFim;
    @NotBlank
        @Size(max = 255)
    private String status;
    @NotBlank
        @Size(max = 255)
    private String observacoes;
    @ManyToOne
        @NotNull
    private Psicologo psicologo;
    @ManyToOne
        @NotNull
    private Paciente paciente;
}
