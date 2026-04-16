package it.marconi.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.marconi.biblioteca.domain.AutoreDTO;
import it.marconi.biblioteca.domain.LibroDTO;
import it.marconi.biblioteca.services.AutoreService;
import it.marconi.biblioteca.services.LibroService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/autori")
public class AutoreController{
    @Autowired
    LibroService libroService;

    @Autowired
    AutoreService autoreService;

    @GetMapping("/")
    @Operation(summary="Recupera tutti gli autori")
    public List<AutoreDTO> getAll(){
        return autoreService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary="Recupera l'autore tramite id")
    public ResponseEntity<AutoreDTO> getAutore(@PathVariable Integer id){

        Optional<AutoreDTO> autore = autoreService.getById(id) ;
        if (autore.isPresent())
            return ResponseEntity.of(autore);
        else
            return ResponseEntity.notFound().build();

        // return autoreService.getById(id).orElseThrow(() -> new RuntimeException("Autore non trovato"));
    }

    @GetMapping("/{id}/libri")
    @Operation(summary="Cerca i libri di un determinato autore") 
    public List<LibroDTO> getLibriByAutore (@PathVariable Integer id){
        return libroService.getByAutoreId(id);
    }

    @PostMapping("/add")
    @Operation(summary="Aggiunge un autore")
    public ResponseEntity<AutoreDTO> addAutore(@Valid @RequestBody AutoreDTO autore){
        AutoreDTO salvato = autoreService.save(autore);

        return ResponseEntity.ok(salvato);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Rimuove un autore del database e anche tutti i suoi libri")
    public ResponseEntity<String> deleteAutore(@PathVariable Integer id){
        boolean deleted = autoreService.deleteById(id);

        if (deleted)
            return ResponseEntity.ok("Autore eliminato correttamente");
        else 
            return ResponseEntity.notFound().build();
    }
}