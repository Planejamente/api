package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService service;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    public AuthResponseDto login(AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.googleSub());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.service.generateToken((Usuario) auth.getPrincipal());
        return new AuthResponseDto(token);
    }
}