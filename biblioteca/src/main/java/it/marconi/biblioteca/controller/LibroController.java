package it.marconi.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import it.marconi.biblioteca.domain.LibroDTO;
import it.marconi.biblioteca.domain.response.APIResponse;
import it.marconi.biblioteca.services.LibroService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/libri")
public class LibroController {
    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService){
        this.libroService=libroService;
    }

    @GetMapping("/")
    @Operation(summary="Recupera tutti i libri")
    public APIResponse<List<LibroDTO>> getAll(){
        // return libroService.findAll();
        List<LibroDTO> listaLibri = libroService.findAll();
        return APIResponse.success(listaLibri);

    }

    @GetMapping("/{id}")
    @Operation(summary="Cerca un libro in base al campo isbn")
    public APIResponse<LibroDTO> getLibroByIsbn(@PathVariable String isbn){
        Optional<LibroDTO> libro = libroService.getByIsbn(isbn);

        return libro.map(APIResponse::success).orElseThrow(()->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND,"Libro non trovato per ISBN"
        ));
    }

    @GetMapping("/libro")
    @Operation(summary="Cerca un libro in base al titolo")
    public APIResponse<LibroDTO> getLibroByTitolo (@RequestParam("titolo") String titolo){
        Optional<LibroDTO> libro = libroService.getByTitolo(titolo);
        // throw new Exception("Si è rotto qualcosa",)

        return libro.map(APIResponse::success).orElseThrow(()->
        new ResponseStatusException(
            HttpStatus.NOT_FOUND,"Libro non trovato per titolo"
        ));
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
            ResponseEntity.noContent().build() :
            ResponseEntity.notFound().build();
    }
}   
