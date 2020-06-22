package com.asde.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "alcaldias")
public class Alcaldia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlcaldia;

    private String nombre;

    public Alcaldia() {
    }

    public Integer getIdAlcaldia() {
        return idAlcaldia;
    }

    public void setIdAlcaldia(Integer idAlcaldia) {
        this.idAlcaldia = idAlcaldia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
} // end of class domain
