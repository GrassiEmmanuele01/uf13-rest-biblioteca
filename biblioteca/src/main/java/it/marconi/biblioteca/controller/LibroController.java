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
import it.marconi.biblioteca.domain.LibroDTO;
import it.marconi.biblioteca.services.LibroService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/libri")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/")
    @Operation(summary="Recupera tutti i libri")
    public List<LibroDTO> getAll(){
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary="Cerca un libro in base al campo isbn")
    public ResponseEntity<LibroDTO> getLibroByIsbn(@PathVariable String isbn){
        Optional<LibroDTO> libro = libroService.getByIsbn(isbn);

        if (libro.isPresent())
            return ResponseEntity.of(libro);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/libro")
    @Operation(summary="Cerca un libro in base al titolo")
    public ResponseEntity<LibroDTO> getLibroByTitolo (@RequestParam("titolo") String titolo){
        Optional<LibroDTO> libro = libroService.getByTitolo(titolo);
        
        if (libro.isPresent())
            return ResponseEntity.of(libro);
        else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/add")    
    @Operation(summary="Aggiunge un libro")
    public ResponseEntity<LibroDTO> addLibro(@Valid @RequestBody LibroDTO libro){
        Optional<LibroDTO> salvato = libroService.save(libro);

        if (salvato.isPresent())
            return ResponseEntity.of(salvato);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{isbn}")
    @Operation(summary = "Cancella un libro dato il suo ISBN")
        public ResponseEntity<String> deleteLibro(@PathVariable String isbn){
        boolean deleted = libroService.deleteByIsbn(isbn);

        return deleted ?
            ResponseEntity.ok("Libro eliminato con successo") :
            ResponseEntity.notFound().build();
    }
}   
