package org.planejamente.planejamente.dto.dtoCriar;



import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.usuario.Psicologo;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienciaFormacaoDto {
    @NotNull
        @Past
    private LocalDate dataInicio;
    @NotNull
        @PastOrPresent
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
    @NotBlank
        @Size(max = 255)
    private String tipo;
    @NotBlank
        @Size(max = 255)
    private String titulo;
    @ManyToOne
        @NotNull
    private Psicologo psicologo;
}