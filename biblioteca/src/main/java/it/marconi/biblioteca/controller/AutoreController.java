package it.marconi.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.marconi.biblioteca.domain.AutoreDTO;
import it.marconi.biblioteca.services.AutoreService;
import it.marconi.biblioteca.services.LibroService;
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
    public List<AutoreDTO> getAll(){
        return autoreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoreDTO> getAutore(@PathVariable Integer id){

        Optional<AutoreDTO> autore = autoreService.getById(id) ;
        if (autore.isPresent())
            return ResponseEntity.of(autore);
        else
            return ResponseEntity.notFound().build();

        // return autoreService.getById(id).orElseThrow(() -> new RuntimeException("Autore non trovato"));
    }

    // @PostMapping("/add")
    // public ResponseEntity<AutoreDTO> addAutore(){

    // }

}