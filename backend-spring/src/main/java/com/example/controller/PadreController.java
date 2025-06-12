package com.example.controller;

import com.example.model.Padre;
import com.example.repository.PadreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/padres")
public class PadreController {

    
    @Autowired
    private PadreRepository padreRepository;

    @GetMapping
    public List<Padre> listarPadres() {
        return padreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padre> obtenerPadre(@PathVariable Integer id) {
        Optional<Padre> padre = padreRepository.findById(id);
        return padre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Padre> crearPadre(@Valid @RequestBody Padre padre) {
        return ResponseEntity.ok(padreRepository.save(padre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Padre> actualizarPadre(@PathVariable Integer id, @Valid @RequestBody Padre padreActualizado) {
        if (!padreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        padreActualizado.setId_padre(id);
        return ResponseEntity.ok(padreRepository.save(padreActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPadre(@PathVariable Integer id) {
        if (!padreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        padreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
 
    }

    
}
