package com.crud.dto.swagger.persocat.service;

import com.crud.dto.swagger.persocat.dto.PersonaDTO;
import com.crud.dto.swagger.persocat.model.Persona;

import java.util.List;

public interface IPersonaService {

    public List<PersonaDTO> getPersonas();

    public Persona findPersona(Long id);

    public void savePersona(PersonaDTO persona);

    public void editPersona(Long id, PersonaDTO personaDTO);

    public void deletePersona(Long id);
}
