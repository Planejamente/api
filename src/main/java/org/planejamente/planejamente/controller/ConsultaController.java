//package org.planejamente.planejamente.controller;
//
//import jakarta.validation.Valid;
//import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
//import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
//import org.planejamente.planejamente.entity.Consulta;
//import org.planejamente.planejamente.entity.usuario.Paciente;
//import org.planejamente.planejamente.entity.usuario.Psicologo;
//import org.planejamente.planejamente.mapper.ConsultaMapper;
//import org.planejamente.planejamente.repository.ConsultaRepository;
//import org.planejamente.planejamente.repository.PacienteRepository;
//import org.planejamente.planejamente.repository.PsicologoRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/consultas")
//public class ConsultaController implements IMetodosGenericos<ConsultaDtoConsultar, ConsultaDto> {
//    private final ConsultaRepository repository;
//    private final PsicologoRepository psicologoRepository;
//    private final PacienteRepository pacienteRepository;
//
//    public ConsultaController(ConsultaRepository repository,
//                              PsicologoRepository psicologoRepository, PacienteRepository pacienteRepository) {
//        this.repository = repository;
//        this.psicologoRepository = psicologoRepository;
//        this.pacienteRepository = pacienteRepository;
//    }
//
//    @Override
//        @GetMapping
//    public ResponseEntity<List<ConsultaDtoConsultar>> listar() {
//        return null;
//    }
//
//    @Override
//        @GetMapping("/{id}")
//    public ResponseEntity<ConsultaDtoConsultar> listarPorId(@PathVariable UUID id) {
//        Consulta consulta = this.repository.findById(id).orElse(null);
//
//        if(consulta == null) return ResponseEntity.notFound().build();
//
//        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consulta);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/psicologo/{idPsicologo}")
//    public ResponseEntity<ConsultaDtoConsultar> listarPorPsicologo(@PathVariable UUID idPsicologo) {
//        Psicologo psicologo = this.psicologoRepository.findById(idPsicologo).orElse(null);
//
//        if(psicologo == null) return ResponseEntity.notFound().build();
//
//        List<Consulta> consultas = this.repository.findByPsicologo_IdEquals(psicologo.getId());
//
//        if(consultas.isEmpty()) return ResponseEntity.noContent().build();
//
//        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consultas);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/paciente/{idPaciente}")
//    public ResponseEntity<ConsultaDtoConsultar> listarPorPaciente(@PathVariable UUID idPaciente) {
//        Paciente paciente = this.pacienteRepository.findById(idPaciente).orElse(null);
//
//        if(paciente == null) return ResponseEntity.notFound().build();
//
//        List<Consulta> consultas = this.repository.findByPaciente_IdEquals(paciente.getId());
//
//        if(consultas.isEmpty()) return ResponseEntity.noContent().build();
//
//        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consultas);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @PostMapping
//    public ResponseEntity<ConsultaDtoConsultar> criar(@RequestBody @Valid ConsultaDto dtoCriacao) {
//        Consulta consulta = ConsultaMapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(consulta)) return ResponseEntity.badRequest().build();
//
//        Consulta consultaCadastrada = this.repository.save(consulta);
//        ConsultaDtoConsultar dto = ConsultaMapper.toDto(consultaCadastrada);
//
//        return ResponseEntity.status(201).body(dto);
//    }
//
//    @Override
//        @PutMapping("/{id}")
//    public ResponseEntity<ConsultaDtoConsultar> atualizar(@RequestBody @Valid ConsultaDto dto, @PathVariable UUID id) {
//        return null;
//    }
//
//    @Override
//        @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
//        Consulta consulta = this.repository.findById(id).orElse(null);
//
//        if(Objects.isNull(consulta)) return ResponseEntity.notFound().build();
//
//        this.repository.delete(consulta);
//        return ResponseEntity.noContent().build();
//    }
//}