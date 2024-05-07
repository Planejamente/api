package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/psicologos")
@SecurityRequirement(name = "auth-api")
public class PsicologoController extends UsuarioController<PsicologoDto> {
    private final PsicologoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PsicologoMapper mapper;

    public PsicologoController(PsicologoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = new PsicologoMapper();
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid PsicologoDto data) {
        var u = this.usuarioRepository.findByEmail(data.getEmail());
        if(u != null) return ResponseEntity.status(409).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getGoogleSub());

        Psicologo psicologo = mapper.toEntity(data);
        psicologo.setGoogleSub(encryptedPassword);

        this.repository.save(psicologo);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDtoConsultar>> listar() {
        List<Psicologo> psicologos = this.repository.findAll();

        if(psicologos.isEmpty()) return ResponseEntity.noContent().build();

        List<PsicologoDtoConsultar> dto = mapper.toDto(psicologos);
        return ResponseEntity.ok(dto);
    }
//
//    @Override
//        @GetMapping("/{id}")
//    public ResponseEntity<PsicologoDtoConsultar> listarPorId(@PathVariable UUID id) {
//        Psicologo psicologo = this.repository.findById(id).orElse(null);
//
//        if(psicologo == null) return ResponseEntity.notFound().build();
//
//        PsicologoDtoConsultar dto = mapper.toDto(psicologo);
//        return ResponseEntity.ok(dto);
//    }
//
//    @GetMapping("/login/{googleSub}")
//    public ResponseEntity<PsicologoDtoConsultar> loginGoogle(@PathVariable String googleSub) {
//        Psicologo psicologo = this.repository.findByGoogleSubEquals(googleSub).orElse(null);
//
//        if(psicologo == null) return ResponseEntity.notFound().build();
//
//        PsicologoDtoConsultar dto = mapper.toDto(psicologo);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Override
//        @PostMapping
//    public ResponseEntity<PsicologoDtoConsultar> criar(@RequestBody @Valid PsicologoDto dtoCriacao) {
//        Psicologo psicologo = mapper.toEntity(dtoCriacao);
//
//        if(Objects.isNull(psicologo)) return ResponseEntity.badRequest().build();
//
//        Psicologo psicologoCadastrado = this.repository.save(psicologo);
//        PsicologoDtoConsultar dto = mapper.toDto(psicologoCadastrado);
//
//        return ResponseEntity.status(201).body(dto);
//    }
//
//    @Override
//        @PutMapping("/{id}")
//    public ResponseEntity<PsicologoDtoConsultar> atualizar(@RequestBody @Valid PsicologoDto dto,
//                                                           @PathVariable UUID id) {
//        return null;
//    }
//
//    @Override
//        @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
//        Psicologo psicologo = this.repository.findById(id).orElse(null);
//
//        if(Objects.isNull(psicologo)) return ResponseEntity.badRequest().build();
//
//        this.repository.delete(psicologo);
//        return ResponseEntity.noContent().build();
//    }
}
