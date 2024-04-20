package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.EspecialidadeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final EspecialidadeRepository repository;

    public EspecialidadeController(EspecialidadeRepository repository) {
        this.repository = repository;
    }
}
