package it.marconi.biblioteca.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="books")
public class Libro {

    @Id
    @Column(name="isbn")
    private String isbn;

    @NotNull
    @Size(max=50)
    @Column(name="title", nullable=false)
    private String titolo;

    @Column(name="genre")
    private String genere;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id", nullable = false)
    private Autore autore;

    @Column(name="year")
    private Integer anno;
}
