package com.crud.dto.swagger.persocat.service;

import com.crud.dto.swagger.persocat.dto.GatoDTO;
import com.crud.dto.swagger.persocat.model.Gato;
import com.crud.dto.swagger.persocat.model.Persona;
import com.crud.dto.swagger.persocat.repository.IGatoRepository;
import com.crud.dto.swagger.persocat.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GatoService implements IGatoService {

    @Autowired
    private IGatoRepository gatoRepo;

    @Autowired
    private IPersonaRepository persoRepo;

    @Override
    public List<GatoDTO> getGatos() {
        List<Gato> gatos = gatoRepo.findAll();
        List<GatoDTO> gatosDTO = new ArrayList<>();
        for (Gato gato : gatos) {
            GatoDTO gatoDTO = new GatoDTO();
            gatoDTO.setNombre(gato.getNombre());
            gatoDTO.setRaza(gato.getRaza());
            gatoDTO.setId_persona(gato.getPersona().getId_persona());
            // Una vez que se han establecido todos los valores del GatoDTO, se agrega a la lista gatosDTO.
            gatosDTO.add(gatoDTO);
        }
        return gatosDTO;
    }

    @Override
    public Gato findGato(Long id) {
        Gato gato = gatoRepo.findById(id).orElse(null);
        return gato;
    }

    @Override
    public void saveGato(GatoDTO gatoDTO) {
        // Convertir GatoDTO a Gato
        Gato gato = new Gato();
        gato.setNombre(gatoDTO.getNombre());
        gato.setRaza(gatoDTO.getRaza());

        // Obtener la persona propietaria del gato por su ID
        Long idPersona = gatoDTO.getId_persona();
        Persona propietario = persoRepo.findById(idPersona).orElse(null);

        // Verificar si se encontró la persona propietaria
        if (propietario != null) {
            // Establecer la persona propietaria del gato
            gato.setPersona(propietario);
            // Guardar el gato en la base de datos
            try {
                // Lógica para guardar la mascota
                gatoRepo.save(gato);
            } catch (DataIntegrityViolationException ex) {
                throw new RuntimeException("Ya existe un gato asociado a esta persona");
            }
        } else {
            // Manejar el caso donde la persona no fue encontrada
            throw new RuntimeException("No se encontró la persona propietaria con el ID proporcionado: " + idPersona);
        }
    }

    @Override
    public void editGato(Long id, GatoDTO gatoDTO) {

        // Buscar el objeto original
        Gato gato = this.findGato(id);

        // Proceso de modificación a nivel lógico
        gato.setNombre(gatoDTO.getNombre());
        gato.setRaza(gatoDTO.getRaza());
        gato.getPersona().setId_persona(gatoDTO.getId_persona());

        // Guardar los cambios
        gatoRepo.save(gato);

    }

    @Override
    public void deleteGato(Long id) {
        gatoRepo.deleteById(id);

    }
}
