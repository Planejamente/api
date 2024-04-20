package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.AcessoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acessos")
public class AcessoController {
    private final AcessoRepository repository;

    public AcessoController(AcessoRepository repository) {
        this.repository = repository;
    }
}
