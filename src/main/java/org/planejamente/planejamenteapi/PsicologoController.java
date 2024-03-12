package org.planejamente.planejamenteapi;

import org.planejamente.planejamenteapi.repositories.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PsicologoController {

    @Autowired
    PsicologoRepository repository;

    public void exemplo() {
    }

}
