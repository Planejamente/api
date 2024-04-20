package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.Psicologo;

@Getter
@Setter
public class EspecialidadePsicologoDto {
    @ManyToOne
        @NotNull
    private Especialidade especialidade;
    @ManyToOne
        @NotNull
    private Psicologo psicologo;
}
