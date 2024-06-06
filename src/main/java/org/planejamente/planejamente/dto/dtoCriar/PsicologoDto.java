package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class PsicologoDto extends UsuarioDto {
    @NotBlank
        @Size(max = 15)
    private String crp;
    @Nullable
        @CNPJ
    private String cnpj;
    @NotBlank
        @CPF
    private String cpf;
    @Nullable
        @Size(max = 255)
    private String linkFotoPerfil;
    @NotBlank
        @Size(max = 255)
    private String idCalendarioDisponivel;
    @NotBlank
        @Size(max = 255)
    private String idCalendarioConsulta;
    @Nullable
        @Size(max = 255)
    private String linkAnamnese;
    @Nullable
        @Size(max = 255)
    private String idAnamnese;
    @Nullable
        @Size(max = 255)
    private String linkFotoDeFundo;

    @NotNull
    private String accessToken;
}
