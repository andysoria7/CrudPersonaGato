package com.crud.dto.swagger.persocat.service;

import com.crud.dto.swagger.persocat.dto.GatoDTO;
import com.crud.dto.swagger.persocat.model.Gato;

import java.util.List;

public interface IGatoService {

    public List<GatoDTO> getGatos();

    public Gato findGato(Long id);

    public void saveGato(GatoDTO gato);

    public void editGato(Long id, GatoDTO gatoDTO);

    public void deleteGato(Long id);
}
