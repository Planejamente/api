package org.planejamente.planejamente.controllers;

import org.planejamente.planejamente.entities.Psicologo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/psicologo")
public class PsicologoController implements IMetodosGenericos<Psicologo> {
    List<Psicologo> psicologos = new ArrayList<>();

    @Override
    @GetMapping
    public ResponseEntity<List<Psicologo>> listar() {
        if(psicologos.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(psicologos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Psicologo> getPorId(@PathVariable UUID id) {
        if(buscarNaListaId(id) >= 0) {
            Psicologo psicologoBuscado = psicologos.get(buscarNaListaId(id));
            return ResponseEntity.status(200).body(psicologoBuscado);
        }

        return ResponseEntity.status(404).build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Psicologo> adicionar(@RequestBody Psicologo p) {
        if(p.getNome().isEmpty()) return ResponseEntity.status(400).build();

        if(p.getEmail().isEmpty()) {
            return ResponseEntity.status(400).build();
        } else if(!p.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return ResponseEntity.status(400).build();
        }

        if(p.getSenha().isEmpty()) {
            return ResponseEntity.status(400).build();
        } else if(p.getSenha().matches("/^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}$/gm")) {
            return ResponseEntity.status(400).build();
        }

        if(p.getCrp().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        psicologos.add(p);
        return ResponseEntity.status(201).body(p);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Psicologo> atualizar(@RequestBody Psicologo p, @PathVariable UUID id) {

        if(p.getNome().isEmpty()) return ResponseEntity.status(400).build();

        if(p.getEmail().isEmpty()) {
            return ResponseEntity.status(400).build();
        } else if(!p.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return ResponseEntity.status(400).build();
        }

        if(p.getSenha().isEmpty()) {
            return ResponseEntity.status(400).build();
        } else if(p.getSenha().matches("/^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}$/gm")) {
            return ResponseEntity.status(400).build();
        }

        if(p.getCrp().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        psicologos.set(buscarNaListaId(id), p);
        return ResponseEntity.status(200).body(p);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(UUID id) {
        if(buscarNaListaId(id) < 0) return ResponseEntity.status(404).build();

        psicologos.remove(buscarNaListaId(id));
        return ResponseEntity.status(200).build();
    }

//    @GetMapping("/ordenado")
//    public ResponseEntity<Psicologo[]> buscarOrdenado() {
//        if(psicologos.isEmpty()) return ResponseEntity.status(204).build();
//
//        Psicologo[] vetorPsicologo = new Psicologo[psicologos.size()];
//
//        for (int i = 0; i < psicologos.size(); i++) {
//            vetorPsicologo[i] = psicologos.get(i);
//        }
//
//        int indMenor = 0;
//        for (int i = 0; i < psicologos.size() - 1; i++) {
//            for (int j = i+1; j < psicologos.size(); j++) {
//                if(vetorPsicologo[j].getNome().compareTo(vetorPsicologo[indMenor].getNome()) < 0) {
//                    indMenor = j;
//                }
//            }
//            Psicologo aux = vetorPsicologo[indMenor];
//            vetorPsicologo[indMenor] = vetorPsicologo[i];
//            vetorPsicologo[i] = aux;
//        }
//
//        return ResponseEntity.status(200).body(vetorPsicologo);
//    }
    public int buscarNaListaId(UUID id) {
        for (int i = 0; i < psicologos.size(); i++) {
            if(psicologos.get(i).getUuid().equals(id)) return i;
        }
        return -1;
    }
}
