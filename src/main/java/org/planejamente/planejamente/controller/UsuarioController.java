package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoCriar.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@CrossOrigin
public abstract class UsuarioController<dtoCreateT extends UsuarioDto> {
    public abstract ResponseEntity register(@RequestBody @Valid dtoCreateT data) throws GeneralSecurityException, IOException;
}