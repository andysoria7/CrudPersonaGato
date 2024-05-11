package com.crud.dto.swagger.persocat.controller;

import com.crud.dto.swagger.persocat.dto.GatoDTO;
import com.crud.dto.swagger.persocat.dto.PersonaDTO;
import com.crud.dto.swagger.persocat.model.Gato;
import com.crud.dto.swagger.persocat.model.Persona;
import com.crud.dto.swagger.persocat.service.IGatoService;
import com.crud.dto.swagger.persocat.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GatoController {
    @Autowired
    private IGatoService gatoServ;

    @Autowired
    private IPersonaService persServ;

    @GetMapping("/gatos")
    public List<GatoDTO> getGatos() {
        return gatoServ.getGatos();
    }

    @GetMapping("gato/{id}")
    public ResponseEntity<Gato> getPersonaById(@PathVariable(value = "id") Long id) {
        Gato gato = gatoServ.findGato(id);

        if(gato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gato);

    }

    @PostMapping("/gato")
    public ResponseEntity<String> saveGato(@RequestBody GatoDTO gatoDTO) {
        try {
            // Verificar si la persona propietaria existe antes de guardar la mascota
            Long idPersona = gatoDTO.getId_persona();
            if (persServ.findPersona(idPersona) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la persona propietaria con el ID proporcionado: " + idPersona);
            }
            // Lógica para guardar la mascota
            gatoServ.saveGato(gatoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Mascota guardada con éxito");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la mascota: " + e.getMessage());
        }
    }

    @PutMapping("/gato/{id}")
    public ResponseEntity<String> editGato(@PathVariable Long id, @RequestBody GatoDTO gatoDTO) {
        try {
            // Verificar si el gato existe antes de editarlo
            Gato gato = gatoServ.findGato(id);
            if (gato == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Gato con el ID " + id + " no existe");
            }

            // Verificar si la persona existe antes de editarla
            Long idPersona = gatoDTO.getId_persona();
            Persona persona = persServ.findPersona(idPersona);
            if (persona == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Persona con el ID " + idPersona + " no existe");
            }

            // Lógica para editar el gato
            gatoServ.editGato(id, gatoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Gato editado con éxito");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el gato: " + e.getMessage());
        }
    }

    @DeleteMapping("/gato/{id}")
    public ResponseEntity<String> eliminarGato(@PathVariable Long id) {
        // Lógica para eliminar el gato
        try {
            // Verificar si el gato existe antes de intentar eliminarlo
            if (gatoServ.findGato(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El gato con el ID " + id + " no existe");
            }

            // Si el gato existe, eliminarlo
            gatoServ.deleteGato(id);
            return ResponseEntity.status(HttpStatus.OK).body("Gato eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el gato " + e.getMessage());
        }
    }
}
