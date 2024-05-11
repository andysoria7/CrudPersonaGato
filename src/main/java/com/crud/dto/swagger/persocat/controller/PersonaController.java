package com.crud.dto.swagger.persocat.controller;

import com.crud.dto.swagger.persocat.dto.PersonaDTO;
import com.crud.dto.swagger.persocat.model.Persona;
import com.crud.dto.swagger.persocat.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private IPersonaService persoServ;

    @GetMapping("/personas")
    public List<PersonaDTO> getPersonas() {
        return persoServ.getPersonas();
    }

    @GetMapping("persona/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable(value = "id") Long id) {
        Persona persona = persoServ.findPersona(id);

        if(persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(persona);

    }

    @PostMapping("/persona")
    public ResponseEntity<Void> savePersona(@RequestBody PersonaDTO personaDTO) {
        persoServ.savePersona(personaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/persona/{id}")
    public ResponseEntity<String> editPersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        try {
            // Verificar si la persona existe antes de editarla
            if (persoServ.findPersona(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Persona con el ID " + id + " no existe");
            }

            // Lógica para editar la persona
            persoServ.editPersona(id, personaDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Persona editada con éxito");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar la Persona: " + e.getMessage());
        }
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        // Lógica para eliminar la persona
        try {
            // Verificar si la persona existe antes de intentar eliminarla
            if (persoServ.findPersona(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Persona con el ID " + id + " no existe");
            }

            // Si la persona existe, eliminarla
            persoServ.deletePersona(id);
            return ResponseEntity.status(HttpStatus.OK).body("Persona eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la Persona " + e.getMessage());
        }
    }

}
