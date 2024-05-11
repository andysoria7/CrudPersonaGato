package com.crud.dto.swagger.persocat.service;

import com.crud.dto.swagger.persocat.dto.PersonaDTO;
import com.crud.dto.swagger.persocat.model.Persona;
import com.crud.dto.swagger.persocat.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private IPersonaRepository persoRepo;

    @Override
    public List<PersonaDTO> getPersonas() {
        List<Persona> personas = persoRepo.findAll();
        List<PersonaDTO> listaPersonasDTO = new ArrayList<>();

        for (Persona persona : personas) {
            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setNombre(persona.getNombre());
            personaDTO.setApellido(persona.getApellido());
            personaDTO.setEdad(persona.getEdad());
            listaPersonasDTO.add(personaDTO);
        }

        return listaPersonasDTO;
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = persoRepo.findById(id).orElse(null);

        return persona;
    }

    @Override
    public void savePersona(PersonaDTO personaDTO) {
        // Convertir PersonaDTO a Persona
        Persona persona = new Persona();
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setEdad(personaDTO.getEdad());

        // Guardar la persona en la base de datos
        persoRepo.save(persona);
    }

    @Override
    public void editPersona(Long id, PersonaDTO personaDTO) {
        // Buscar el objeto original
        Persona persona = this.findPersona(id);

        // Proceso de modificación a nivel lógico
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setEdad(personaDTO.getEdad());

        // Guardar los cambios
        persoRepo.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        persoRepo.deleteById(id);

    }
}
