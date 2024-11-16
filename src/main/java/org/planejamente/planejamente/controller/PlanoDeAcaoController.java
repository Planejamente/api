package org.planejamente.planejamente.controller;

import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Put;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.planejamente.planejamente.dto.TituloPlanoDeAcao;
import org.planejamente.planejamente.dto.dtoConsultar.EnderecoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PlanoDeAcaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoDto;
import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoTarefasDto;
import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;
import org.planejamente.planejamente.service.PlanoDeAcaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "auth-api")
@RestController
@RequestMapping("/plano-de-acao")
@CrossOrigin
public class PlanoDeAcaoController {
    private final PlanoDeAcaoService service;

    public PlanoDeAcaoController(PlanoDeAcaoService service) {
        this.service = service;
    }

    @GetMapping("/consulta/{idConsulta}")
    public ResponseEntity<List<PlanoDeAcaoDtoConsultar>> buscarPorConsulta(@PathVariable UUID idConsulta) {
        List<PlanoDeAcaoDtoConsultar> lista = this.service.buscarPorConsulta(idConsulta);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PlanoDeAcaoDtoConsultar>> buscarPorUsuario(@PathVariable UUID idUsuario) {
        List<PlanoDeAcaoDtoConsultar> lista = this.service.buscarPorUsuario(idUsuario);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<PlanoDeAcaoDtoConsultar> salvar(@RequestBody @Valid PlanoDeAcaoDto dtoConsultar) {
        PlanoDeAcaoDtoConsultar dto = this.service.salvar(dtoConsultar);
        return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{idPlanoDeAcao}/{idTarefa}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> atualizarTarefa(@PathVariable UUID idPlanoDeAcao, @PathVariable String idTarefa) {
        PlanoDeAcaoDtoConsultar dto = this.service.atualizarTarefa(idPlanoDeAcao, idTarefa);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{idPlanoDeAcao}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> atualizar(@PathVariable UUID idPlanoDeAcao) {
        PlanoDeAcaoDtoConsultar dto = this.service.atualizar(idPlanoDeAcao);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/adicionar-tarefa/{idPlanoDeAcao}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> adicionarTarefa(@PathVariable UUID idPlanoDeAcao, @RequestBody @Valid PlanoDeAcaoTarefasDto dtoPdat) {
        PlanoDeAcaoDtoConsultar dto = this.service.adicionarTarefa(idPlanoDeAcao, dtoPdat);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/editar-tarefa/{idPlanoDeAcao}/{idTarefa}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> atualizarTarefa(@PathVariable UUID idPlanoDeAcao, @PathVariable String idTarefa, @RequestBody @Valid PlanoDeAcaoTarefasDto dtoPdat) {
        PlanoDeAcaoDtoConsultar dto = this.service.editarTarefa(idPlanoDeAcao, idTarefa, dtoPdat);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/remover-tarefa/{idPlanoDeAcao}/{idTarefa}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> deletarTarefa(@PathVariable UUID idPlanoDeAcao, @PathVariable String idTarefa) {
        PlanoDeAcaoDtoConsultar dto = this.service.deletarTarefa(idPlanoDeAcao, idTarefa);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/editar-titulo/{idPlanoDeAcao}")
    public ResponseEntity<PlanoDeAcaoDtoConsultar> editarTitulo(@PathVariable UUID idPlanoDeAcao, @RequestBody @Valid TituloPlanoDeAcao record) {
        PlanoDeAcaoDtoConsultar dtoConsultar = this.service.editarTitulo(idPlanoDeAcao, record.tituloPlanoDeAcao());
        return ResponseEntity.status(200).body(dtoConsultar);
    }
}
