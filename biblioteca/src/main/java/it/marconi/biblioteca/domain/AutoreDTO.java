package it.marconi.biblioteca.domain;

import jakarta.validation.constraints.NotBlank;

public record AutoreDTO(
    Integer id,
    
    @NotBlank(message="Il nome è obbligatorio")
    String nome,

    @NotBlank(message="Il cognome è obbligatorio")
    String cognome
) {}
