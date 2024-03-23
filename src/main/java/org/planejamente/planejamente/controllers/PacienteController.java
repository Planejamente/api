package org.planejamente.planejamente.controllers;

import org.planejamente.planejamente.entities.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements IMetodosGenericos<Paciente> {
    List<Paciente> pacientes = new ArrayList<>();

    @Override
    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        if(pacientes.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(pacientes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPorId(@PathVariable UUID id) {
        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        Paciente pacienteBuscado = pacientes.get(buscarNaListaId(id));
        return ResponseEntity.status(200).body(pacienteBuscado);
    }

    @Override
    @PostMapping
    public ResponseEntity<Paciente> adicionar(@RequestBody Paciente p) {
        pacientes.add(p);
        return ResponseEntity.status(201).body(p);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente p, @PathVariable UUID id) {
        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        pacientes.set(buscarNaListaId(id), p);
        return ResponseEntity.status(200).body(p);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(UUID id) {
        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        pacientes.remove(buscarNaListaId(id));
        return ResponseEntity.status(200).build();
    }

    public int buscarNaListaId(UUID id) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getUuid().equals(id)) return i;
        }
        return -1;
    }
}
