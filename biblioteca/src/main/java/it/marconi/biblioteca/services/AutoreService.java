package it.marconi.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.marconi.biblioteca.domain.Autore;
import it.marconi.biblioteca.domain.AutoreDTO;
import it.marconi.biblioteca.domain.AutoreMapper;
import it.marconi.biblioteca.repositories.AutoreRepository;

@Service
public class AutoreService {
    
    @Autowired
    private AutoreRepository autoreRepo;

    @Autowired
    private AutoreMapper mapper;

    public AutoreDTO save(AutoreDTO autore){
        Autore entity = mapper.toEntity(autore);
        entity.setId(null);
        entity= autoreRepo.save(entity);
        
        return mapper.toDto(entity);
    }

    public List<AutoreDTO> findAll(){
        return autoreRepo.findAll().stream().map(
            autore -> mapper.toDto(autore)).toList();
    }

    public Optional<AutoreDTO> getById(int id){
        return autoreRepo.findById(id).map(mapper::toDto); //ALTRO MODO PER SCRIVERE LANDA FUNCTION 
    }
}
