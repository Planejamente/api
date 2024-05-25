package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.dto.UserTypeDto;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.entity.usuario.UsuarioRole;
import org.planejamente.planejamente.infra.security.TokenService;
import org.planejamente.planejamente.mapper.PacienteMapper;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.planejamente.planejamente.service.AuthenticationService;
import org.planejamente.planejamente.service.AuthorizationService;
import org.planejamente.planejamente.service.PsicologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {


    private final UsuarioRepository repository;
    private final AuthenticationService service;
    private final PacienteMapper pacienteMapper;
    private final PsicologoService psicologoService;

    public AuthenticationController(UsuarioRepository repository, AuthenticationService service, PsicologoService psicologoService) {
        this.repository = repository;
        this.service = service;
        this.pacienteMapper = new PacienteMapper();
        this.psicologoService = psicologoService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        AuthResponseDto dto = this.service.login(data);
        return dto.token().isBlank() ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/crp")
    public ResponseEntity<Boolean> buscarPorCrp(@RequestParam String crp) {
        Boolean exists = this.psicologoService.buscarPorCrp(crp);
        return ResponseEntity.ok(exists);
    }
}