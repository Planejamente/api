package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
    @NotBlank
        @Size(max = 9)
    private String cep;
    @NotBlank
        @Size(max = 122)
    private String rua;
    @NotBlank
        @Size(max = 122)
    private String estado;
    @NotBlank
        @Size(max = 122)
    private String cidade;
}
