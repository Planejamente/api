package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoCriar.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class UsuarioController<dtoCreateT extends UsuarioDto> {
    public abstract ResponseEntity register(@RequestBody @Valid dtoCreateT data);
}