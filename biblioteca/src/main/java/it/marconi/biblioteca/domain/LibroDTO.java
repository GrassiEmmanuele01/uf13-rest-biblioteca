package it.marconi.biblioteca.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LibroDTO(

    @NotBlank(message = "ISBN è obbligatorio")
    @Size(max=13)
    String isbn,

    @NotBlank(message = "Il titolo non può essere vuoto")
    String titolo,

    String genere,

    Integer anno,

    @NotNull(message="ID autore obbligatorio")
    Integer autore
) {}
