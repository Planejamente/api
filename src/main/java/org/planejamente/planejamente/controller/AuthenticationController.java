package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.infra.security.TokenService;
import org.planejamente.planejamente.mapper.PacienteMapper;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final PacienteMapper pacienteMapper;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
        this.pacienteMapper = new PacienteMapper();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.googleSub());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}