package it.marconi.biblioteca.domain;

import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    public LibroDTO toDto(Libro entity){
        if (entity==null) return null;

        return new LibroDTO(
            entity.getIsbn(), 
            entity.getTitolo(), 
            entity.getGenere(),
            entity.getAnno(),
            entity.getAutore() != null ? entity.getAutore().getId() : null 
        );
    }
    public Libro toEntity (LibroDTO dto, Autore autore){
        if (dto==null) return null;

        Libro libro = new Libro();
        libro.setIsbn(dto.isbn());
        libro.setTitolo(dto.titolo());
        libro.setGenere(dto.genere());
        libro.setAnno(dto.anno());
        libro.setAutore(autore);

        return libro;
   }
}
