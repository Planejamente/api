package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psicologos")
public class PsicologoController {
    private final PsicologoRepository repository;

    public PsicologoController(PsicologoRepository repository) {
        this.repository = repository;
    }
}
