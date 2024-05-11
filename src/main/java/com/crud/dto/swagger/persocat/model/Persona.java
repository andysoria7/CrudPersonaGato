package com.crud.dto.swagger.persocat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_persona;
    private String nombre;
    private String apellido;
    private int edad;

    public Persona() {

    }

    public Persona(Long id_persona, String nombre, String apellido, int edad) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
}
