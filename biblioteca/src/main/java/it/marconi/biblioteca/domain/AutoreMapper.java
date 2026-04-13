package it.marconi.biblioteca.domain;

import org.springframework.stereotype.Component;

@Component
public class AutoreMapper {
    public AutoreDTO toDto(Autore entity){
        if (entity==null) return null;
        
        return new AutoreDTO(entity.getId(),entity.getNome() , entity.getCognome());
    }

   public Autore toEntity (AutoreDTO dto){
    if (dto==null) return null;
    Autore entity = new Autore();
    entity.setId(dto.id());
    entity.setNome(dto.nome());
    entity.setCognome(dto.cognome());

    return entity;
   }
}
