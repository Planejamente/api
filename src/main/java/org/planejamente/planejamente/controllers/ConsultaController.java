package org.planejamente.planejamente.controllers;

import org.planejamente.planejamente.entities.Consulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaController implements IMetodosGenericos<Consulta> {
    List<Consulta> consultas = new ArrayList<>();

    @Override
    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        if(consultas.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(consultas);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getPorId(@PathVariable UUID id) {
        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        Consulta consultaBuscada = consultas.get(buscarNaListaId(id));
        return ResponseEntity.status(200).body(consultaBuscada);
    }

    @Override
    public ResponseEntity<Consulta> adicionar(Consulta c) {
        return null;
    }

    @Override
    public ResponseEntity<Consulta> atualizar(Consulta c, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletar(UUID id) {
        return null;
    }

    public int buscarNaListaId(UUID id) {
        for (int i = 0; i < consultas.size(); i++) {
            if(consultas.get(i).getUuid().equals(id)) return i;
        }

        return -1;
    }

}
