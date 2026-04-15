package it.marconi.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.marconi.biblioteca.domain.Libro;
import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, String> {
    
    List<Libro> findByAutoreId(int autoreId);
}
