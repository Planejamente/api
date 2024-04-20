package org.planejamente.planejamente.dto.dtoCriar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoDto {
    @NotBlank(message = "email não pode ser vazio.")
        @Email(regexp = "/^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$/i", message = "email inválido")
            @Size(max = 255)
    private String email;
    @NotBlank(message = "senha não pode ser vazia.")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message = "formato da senha inválida")
            @Size(max = 255)
    private String senha;
    @NotBlank
        @Size(max = 255)
    private String salt;
    @Nullable
        @Size(max = 255)
    private String googleOAuthToken;
}
