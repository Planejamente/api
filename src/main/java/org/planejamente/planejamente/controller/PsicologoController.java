package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.planejamente.planejamente.service.PsicologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/psicologos")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class PsicologoController extends UsuarioController<PsicologoDto> {
    private final PsicologoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PsicologoMapper mapper;
    private final PsicologoService service;

    public PsicologoController(PsicologoRepository repository, UsuarioRepository usuarioRepository, PsicologoService psicologoService) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.service = psicologoService;
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
        List<PsicologoDtoConsultar> lista = this.service.listarTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologoDtoConsultar> buscarPorId(@PathVariable UUID id) {
        PsicologoDtoConsultar psicologoBuscado = this.service.buscarPorId(id);
        return Objects.isNull(psicologoBuscado) ? ResponseEntity.notFound().build() : ResponseEntity.ok(psicologoBuscado);
    }

    @GetMapping("/filtro-genero/{genero}")
    public ResponseEntity<List<PsicologoDtoConsultar>> listarPorGenero(@PathVariable String genero) {
        List<PsicologoDtoConsultar> lista = this.service.listarPorGenero(genero);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }
}