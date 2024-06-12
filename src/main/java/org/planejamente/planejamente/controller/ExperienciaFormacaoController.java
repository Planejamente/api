package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.ExperienciaFormacaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ExperienciaFormacaoDto;
import org.planejamente.planejamente.service.ExperienciaFormacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/experiencias-formacoes")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class ExperienciaFormacaoController {

    private final ExperienciaFormacaoService service;

    public ExperienciaFormacaoController(ExperienciaFormacaoService service) {
        this.service = service;
    }

    @GetMapping("/{idPsicologo}")
    public ResponseEntity<List<ExperienciaFormacaoDtoConsultar>> buscarPorPsicologo(@PathVariable UUID idPsicologo) {
        List<ExperienciaFormacaoDtoConsultar> lista = this.service.buscarPorPsicologo(idPsicologo);

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ExperienciaFormacaoDtoConsultar> adicionar(@RequestBody @Valid ExperienciaFormacaoDto expForm) {
        ExperienciaFormacaoDtoConsultar dto = this.service.criar(expForm);
        return ResponseEntity.status(201).body(dto);
    }
}