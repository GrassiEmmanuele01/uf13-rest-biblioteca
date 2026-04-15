package it.marconi.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.marconi.biblioteca.domain.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Integer>{
    
}
