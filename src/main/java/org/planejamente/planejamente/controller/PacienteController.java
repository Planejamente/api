package org.planejamente.planejamente.controller;

import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.dto.dtoConsultar.PacienteDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PacienteDto;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.PacienteMapper;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends UsuarioController<PacienteDto> {

    private final PacienteRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PacienteMapper mapper;

    public PacienteController(PacienteRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = new PacienteMapper();
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(PacienteDto data) {
        var u = this.usuarioRepository.findByEmail(data.getEmail());
        if(u != null) return ResponseEntity.status(409).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getGoogleSub());

        Paciente paciente = mapper.toEntity(data);
        paciente.setGoogleSub(encryptedPassword);

        this.repository.save(paciente);
        return ResponseEntity.status(201).build();
    }

//    @Override
//        @GetMapping
//    public ResponseEntity<List<PacienteDtoConsultar>> listar() {
//        List<Paciente> pacientes = this.repository.findAll();
//
//        if(pacientes.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<PacienteDtoConsultar> dto = mapper.toDto(pacientes);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @GetMapping("/{id}")
//    public ResponseEntity<PacienteDtoConsultar> listarPorId(@PathVariable UUID id) {
//        Paciente paciente = this.repository.findById(id).orElse(null);
//
//        if(paciente == null) return ResponseEntity.notFound().build();
//
//        PacienteDtoConsultar dto = mapper.toDto(paciente);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/login/{googleSub}")
//    public ResponseEntity<PacienteDtoConsultar> loginGoogle(@PathVariable String googleSub) {
//        Paciente paciente = this.repository.findByGoogleSubEquals(googleSub).orElse(null);
//
//        if(paciente == null) return ResponseEntity.notFound().build();
//
//        PacienteDtoConsultar dto = mapper.toDto(paciente);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @PostMapping
//    public ResponseEntity<PacienteDtoConsultar> criar(@RequestBody @Valid PacienteDto dtoCriacao) {
//        Paciente paciente = mapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(paciente)) return ResponseEntity.badRequest().build();
//
//        Paciente pacienteCadastrado = this.repository.save(paciente);
//        PacienteDtoConsultar dto = mapper.toDto(pacienteCadastrado);
//
//        return ResponseEntity.status(201).body(dto);
//    }
//
//    @Override
//        @PutMapping("/{id}")
//    public ResponseEntity<PacienteDtoConsultar> atualizar(@RequestBody @Valid PacienteDto dto,
//                                                          @PathVariable UUID id) {
//        return null;
//    }
//
//    @Override
//        @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
//        Paciente paciente = this.repository.findById(id).orElse(null);
//
//        if(Objects.isNull(paciente)) return ResponseEntity.notFound().build();
//
//        this.repository.delete(paciente);
//        return ResponseEntity.noContent().build();
//    }
}
