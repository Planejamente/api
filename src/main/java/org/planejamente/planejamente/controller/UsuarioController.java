package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoCriar.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@SecurityRequirement(name = "auth-api")
public abstract class UsuarioController<dtoCreateT extends UsuarioDto> {
    public abstract ResponseEntity register(@RequestBody @Valid dtoCreateT data);
}