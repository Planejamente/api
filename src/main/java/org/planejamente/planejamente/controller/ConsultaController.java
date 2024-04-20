package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.ConsultaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaRepository repository;

    public ConsultaController(ConsultaRepository repository) {
        this.repository = repository;
    }
}
