package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
    @Nullable
    private UUID idUsuario;
}
