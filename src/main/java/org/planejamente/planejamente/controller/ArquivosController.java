package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.ArquivosRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arquivos")
public class ArquivosController {
    private final ArquivosRepository repository;

    public ArquivosController(ArquivosRepository repository) {
        this.repository = repository;
    }
}
