package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.EspecialidadePsicologoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/especialidades-psicologos")
public class EspecialidadePsicologoController {
    private final EspecialidadePsicologoRepository repository;

    public EspecialidadePsicologoController(EspecialidadePsicologoRepository repository) {
        this.repository = repository;
    }
}
