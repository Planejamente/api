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
import org.planejamente.planejamente.service.PacienteService;
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
    private final PacienteService service;

    public PacienteController(PacienteRepository repository, UsuarioRepository usuarioRepository, PacienteService service) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.service = service;
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

    @GetMapping
    public ResponseEntity<List<PacienteDtoConsultar>> listar() {
        List<PacienteDtoConsultar> lista = this.service.listarTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoConsultar> buscarPorId(@PathVariable UUID id) {
        PacienteDtoConsultar pacienteBuscado = this.service.buscarPorId(id);
        return Objects.isNull(pacienteBuscado) ? ResponseEntity.notFound().build() : ResponseEntity.ok(pacienteBuscado);
    }
}
