package com.crud.dto.swagger.persocat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Gato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mascota;
    private String nombre;
    private String raza;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

    public Gato() {

    }

    public Gato(Long id_mascota, String nombre, String raza) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.raza = raza;
    }

}
