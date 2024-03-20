package org.planejamente.planejamente.controllers;

import org.planejamente.planejamente.entities.Psicologo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public interface IMetodosGenericos<T>{
    public ResponseEntity<List<T>> listar();
    public ResponseEntity<T> getPorId(UUID id);
    public ResponseEntity<T> adicionar(T object);
    public ResponseEntity<T> atualizar(T object, UUID id);
    public ResponseEntity<Void> deletar(UUID id);
}
