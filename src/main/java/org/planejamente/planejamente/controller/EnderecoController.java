package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
import org.planejamente.planejamente.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("enderecos")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class EnderecoController {
    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<EnderecoDtoConsultar> listarPorUsuario(@PathVariable UUID idUsuario) {
        EnderecoDtoConsultar dto = this.service.buscarPorId(idUsuario);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EnderecoDtoConsultar> criar(@RequestBody @Valid EnderecoDto dtoCriacao) {
        EnderecoDtoConsultar dto = this.service.salvar(dtoCriacao);
        return ResponseEntity.status(201).body(dto);
    }
}