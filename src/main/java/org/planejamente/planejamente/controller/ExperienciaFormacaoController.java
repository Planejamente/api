package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.ExperienciaFormacaoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencias-formacoes")
public class ExperienciaFormacaoController {
    private final ExperienciaFormacaoRepository repository;

    public ExperienciaFormacaoController(ExperienciaFormacaoRepository repository) {
        this.repository = repository;
    }
}
