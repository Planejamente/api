package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.dtoAtualizar.PsicologoDtoAtualizar;
import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoExibir;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.dto.dtoCriar.RelatorioMensalDTO;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.planejamente.planejamente.service.EnderecoService;
import org.planejamente.planejamente.service.PsicologoService;
import org.planejamente.planejamente.service.RelatorioMensalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
    private final RelatorioMensalService relatorioMensalService;
    private final EnderecoService cepService;
    public PsicologoController(PsicologoRepository repository, UsuarioRepository usuarioRepository, PsicologoService psicologoService, RelatorioMensalService relatorioMensalService, EnderecoService cepService) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.service = psicologoService;
        this.cepService = cepService;
        this.mapper = new PsicologoMapper();
        this.relatorioMensalService = relatorioMensalService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid PsicologoDto data) throws GeneralSecurityException, IOException {
        this.service.salvar(data);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<PsicologoDtoConsultar>> listar() {
        List<PsicologoDtoConsultar> lista = this.service.listarTodos();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologoDtoExibir> buscarPorId(@PathVariable UUID id) {
        PsicologoDtoExibir psicologoBuscado = this.service.buscarPorId(id);

        return ResponseEntity.ok(psicologoBuscado);
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


    @PutMapping("/{idPsicologo}")
    public ResponseEntity<PsicologoDtoConsultar> atualizar(@RequestBody @Valid PsicologoDtoAtualizar dto, @PathVariable UUID idPsicologo) {
        PsicologoDtoConsultar psiAtualizado = this.service.atualizar(dto, idPsicologo);
        EnderecoDtoConsultar enderecoAtt = this.cepService.atualizar(String.valueOf(idPsicologo), dto.getCep());
        return ResponseEntity.ok(psiAtualizado);
    }

    @GetMapping("/{id}/relatorio-mensal/{mes}")
    public ResponseEntity<RelatorioMensalDTO> getRelatorioMensal(@PathVariable UUID id, @PathVariable int mes) {
        RelatorioMensalDTO relatorio = relatorioMensalService.gerarRelatorioMensal(id, mes);
        return ResponseEntity.ok(relatorio);
    }


}