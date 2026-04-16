package it.marconi.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.marconi.biblioteca.domain.Autore;
import it.marconi.biblioteca.domain.Libro;
import it.marconi.biblioteca.domain.LibroDTO;
import it.marconi.biblioteca.domain.LibroMapper;
import it.marconi.biblioteca.repositories.AutoreRepository;
import it.marconi.biblioteca.repositories.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private AutoreRepository autoreRepo;
    
    @Autowired
    private LibroMapper mapper;

    public List<LibroDTO> findAll(){
        return libroRepo.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<LibroDTO> getByIsbn(String isbn){
        return libroRepo.findById(isbn).map(mapper::toDto);
    } 

    public Optional<LibroDTO> save(LibroDTO libro){
        Optional<Autore> autore =autoreRepo.findById(libro.autore());
        
        if (autore.isPresent()){
            Autore a = autore.get();
            Libro entity = mapper.toEntity(libro, a);

            return Optional.of(mapper.toDto(libroRepo.save(entity)));
        }
        else
            return Optional.empty();
    }

    public Optional<LibroDTO> getByTitolo (String titolo){
        return libroRepo.findAll().stream()
        .filter(libro -> libro.getTitolo().equals(titolo)).findFirst()
        .map(mapper::toDto);
    }

    //ricerca del titolo per autoreId (query custom)
    public List<LibroDTO> getByAutoreId(int autoreId){
        return libroRepo.findByAutoreId(autoreId).stream().map(mapper::toDto).toList();
    }

    public boolean deleteByIsbn(String isbn){
     if (libroRepo.existsById(isbn)){
            libroRepo.deleteById(isbn);
            return true;
        }else
            return false;
    }
}
