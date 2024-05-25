package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultaDto {
    @CreationTimestamp
    private LocalDateTime dtCriacao;
    @NotBlank
    private UUID idPsicologo;
    @NotBlank
    private UUID idPaciente;
    @NotBlank
        @Size(max = 255)
    private String linkMeet;
    @NotBlank
        @Size(max = 255)
    private String linkAnamnese;
    @NotNull
        @FutureOrPresent
    private LocalDateTime inicio;
    @NotNull
        @FutureOrPresent
    private LocalDateTime fim;
    @NotBlank
        @Size(max = 255)
    private String idAnamnese;
}