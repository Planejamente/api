package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Psicologo;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienciaFormacaoDto {
    @NotNull
        @Past
    private LocalDate dataInicio;
    @NotNull
        @Past
    private LocalDate dataFim;
    @NotBlank
        @Size(max = 255)
    private String instituicao;
    @NotBlank
        @Size(max = 255)
    private String cargo;
    @NotBlank
        @Size(max = 255)
    private String descricao;
    @ManyToOne
        @NotNull
    private Psicologo psicologo;
    @NotBlank
        @Size(max = 255)
    private String tipo;
    @NotBlank
        @Size(max = 255)
    private String titulo;
}
