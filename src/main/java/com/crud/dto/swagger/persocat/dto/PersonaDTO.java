package com.crud.dto.swagger.persocat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonaDTO {

    private String nombre;
    private String apellido;
    private int edad;

    public PersonaDTO() {

    }

    public PersonaDTO(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
}
