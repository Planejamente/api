package org.planejamente.planejamente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public interface IMetodosGenericos<consultarT, criarT>{
    public ResponseEntity<List<consultarT>> listar();
    public ResponseEntity<consultarT> listarPorId(UUID id);
    public ResponseEntity<consultarT> criar(criarT dto);
    public ResponseEntity<consultarT> atualizar(criarT dto, UUID id);
    public ResponseEntity<Void> deletar(UUID id);
}