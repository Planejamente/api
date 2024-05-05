//package org.planejamente.planejamente.controller;
//
//import jakarta.validation.Valid;
//import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeDtoConsultar;
//import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeDto;
//import org.planejamente.planejamente.entity.Especialidade;
//import org.planejamente.planejamente.mapper.EspecialidadeMapper;
//import org.planejamente.planejamente.repository.EspecialidadeRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/especialidades")
//public class EspecialidadeController {
//    private final EspecialidadeRepository repository;
//
//    public EspecialidadeController(EspecialidadeRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<EspecialidadeDtoConsultar>> listar() {
//        List<Especialidade> especialidades = this.repository.findAll();
//
//        if(especialidades.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<EspecialidadeDtoConsultar> dto = EspecialidadeMapper.toDto(especialidades);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<EspecialidadeDtoConsultar> listarPorId(@PathVariable UUID id) {
//        Especialidade especialidade = this.repository.findById(id).orElse(null);
//
//        if(especialidade == null) return ResponseEntity.notFound().build();
//
//        EspecialidadeDtoConsultar dto = EspecialidadeMapper.toDto(especialidade);
//        return ResponseEntity.ok(dto);
//    }
//}