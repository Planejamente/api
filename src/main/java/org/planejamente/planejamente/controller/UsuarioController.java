package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class UsuarioController<dtoCreateT> {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public abstract ResponseEntity register(@RequestBody @Valid dtoCreateT data);

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}