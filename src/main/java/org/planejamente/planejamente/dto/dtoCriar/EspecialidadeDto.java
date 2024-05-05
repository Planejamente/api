package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeDto {
    @NotBlank
        @Size(max = 255)
    private String descricao;
    @NotBlank
        @Size(max = 45)
    private String titulo;
}