package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.PsicologosDisponiveisDto;
import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.mapper.ConsultaMapper;
import org.planejamente.planejamente.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
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
    public ResponseEntity<List<ConsultaDtoConsultar>> buscarPorPsicologo(@PathVariable UUID idPsicologo) {
        List<ConsultaDtoConsultar> lista = this.service.buscarPorPsicologo(idPsicologo);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<ConsultaDtoConsultar>> buscarPorPaciente(@PathVariable UUID idPaciente) {
        List<ConsultaDtoConsultar> lista = this.service.buscarPorPaciente(idPaciente);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ConsultaDtoConsultar> criar(@RequestBody @Valid ConsultaDto consultaDto) throws GeneralSecurityException, IOException {
        System.out.println(consultaDto);

        String accessToken = consultaDto.getAccessToken();
        String calendarId = consultaDto.getCalendarId();

        Consulta consultaSalva = this.service.criar(consultaDto, accessToken, calendarId);

        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consultaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDtoConsultar>> buscarTodos(@RequestBody PsicologosDisponiveisDto dto){
        return ResponseEntity.ok(service.buscarTodos(dto));

    }
}