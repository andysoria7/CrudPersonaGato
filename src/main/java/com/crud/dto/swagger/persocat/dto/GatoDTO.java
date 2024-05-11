package com.crud.dto.swagger.persocat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GatoDTO {

    private String nombre;
    private String raza;
    private Long id_persona;

    public GatoDTO() {

    }

    public GatoDTO(String nombre, String raza) {
        this.nombre = nombre;
        this.raza = raza;
        this.id_persona = id_persona;
    }
}
