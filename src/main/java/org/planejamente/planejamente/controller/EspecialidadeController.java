package org.planejamente.planejamente.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeDto;
import org.planejamente.planejamente.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/especialidades")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class EspecialidadeController {
    private final EspecialidadeService service;

    public EspecialidadeController(EspecialidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeDtoConsultar>> listar() {
        List<EspecialidadeDtoConsultar> lista = this.service.listar();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<EspecialidadeDtoConsultar> criar(@RequestBody @Valid EspecialidadeDto dto) {
        EspecialidadeDtoConsultar dtoSalva = this.service.criar(dto);
        return ResponseEntity.status(201).body(dtoSalva);
    }

    @DeleteMapping("/{idEspecialidade}")
    public ResponseEntity<Void> deletar(@PathVariable UUID idEspecialidade) {
        this.service.deletar(idEspecialidade);
        return ResponseEntity.noContent().build();
    }
}