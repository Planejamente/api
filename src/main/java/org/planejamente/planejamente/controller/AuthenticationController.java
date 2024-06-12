package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.service.AuthenticationService;
import org.planejamente.planejamente.service.PsicologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@SecurityRequirement(name = "auth-api")
public class AuthenticationController {

    private final AuthenticationService service;
    private final PsicologoService psicologoService;

    public AuthenticationController(AuthenticationService service, PsicologoService psicologoService) {
        this.service = service;
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