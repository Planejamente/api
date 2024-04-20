package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.planejamente.planejamente.entity.Consulta;

@Getter
@Setter
public class AvaliacaoDto {
    @OneToOne
        @NotNull
    private Consulta consulta;
    @NotBlank
        @Size(max = 255)
    private String descricao;
    @NotNull
        @DecimalMin(value = "0,0")
        @DecimalMax(value = "5,0")
    private Double nota;
}
