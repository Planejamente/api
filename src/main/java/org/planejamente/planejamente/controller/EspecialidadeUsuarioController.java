package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeUsuarioDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeUsuarioDto;
import org.planejamente.planejamente.service.EspecialidadeUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/especialidades-usuarios")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class EspecialidadeUsuarioController {
    private final EspecialidadeUsuarioService service;

    public EspecialidadeUsuarioController(EspecialidadeUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<EspecialidadeUsuarioDtoConsultar>> criar(@RequestBody @Valid EspecialidadeUsuarioDto dto) {
        List<EspecialidadeUsuarioDtoConsultar> lista = this.service.criar(dto);
        return ResponseEntity.status(201).body(lista);
    }

    @GetMapping("/{idPsicologo}")
    public ResponseEntity<List<EspecialidadeUsuarioDtoConsultar>> buscarPorPsicologo(@PathVariable UUID idPsicologo) {
        List<EspecialidadeUsuarioDtoConsultar> lista = this.service.listarPorPsicologo(idPsicologo);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }
}