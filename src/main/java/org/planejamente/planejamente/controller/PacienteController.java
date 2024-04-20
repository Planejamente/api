package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.PacienteRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }
}
