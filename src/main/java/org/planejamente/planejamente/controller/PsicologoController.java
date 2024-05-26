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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        this.service.salvar(data);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDtoConsultar>> listar() {
        List<PsicologoDtoConsultar> lista = this.service.listarTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologoDtoConsultar> buscarPorId(@PathVariable UUID id) {
        Psicologo psicologoBuscado = this.service.buscarPorId(id);
        PsicologoDtoConsultar dto = PsicologoMapper.toDto(psicologoBuscado);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro-genero/{genero}")
    public ResponseEntity<List<PsicologoDtoConsultar>> listarPorGenero(@PathVariable String genero) {
        List<PsicologoDtoConsultar> lista = this.service.listarPorGenero(genero);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/ordenado")
    public ResponseEntity<List<PsicologoDtoConsultar>> listarOrdenado() {
        List<PsicologoDtoConsultar> listaOrdenada = this.service.listarOrdenado();
        return listaOrdenada.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaOrdenada);
    }

    @GetMapping("/matriz/{colunas}")
    public ResponseEntity<List<List<PsicologoDtoConsultar>>> listarEmMatriz(@PathVariable int colunas) {
        List<List<PsicologoDtoConsultar>> matriz = this.service.listarEmMatriz(colunas);
        return matriz.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(matriz);
    }

}