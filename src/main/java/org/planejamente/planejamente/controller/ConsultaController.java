package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.mapper.ConsultaMapper;
import org.planejamente.planejamente.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping("/psicologo/{idPsicologo}")
    public ResponseEntity<ConsultaDtoConsultar> buscarPorPsicologo(@PathVariable UUID idPsicologo) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ConsultaDtoConsultar> criar(@RequestBody @Valid ConsultaDto consultaDto) {
        System.out.println(consultaDto);
        Consulta consultaSalva = this.service.criar(consultaDto);
        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consultaSalva);

        return ResponseEntity.status(201).body(dto);
    }
}