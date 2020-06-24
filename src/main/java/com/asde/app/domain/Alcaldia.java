package com.asde.app.domain;

import javax.persistence.*;

/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>alcaldias</b> en la BD, con el fin de poder
 * representar los datos de una <i>alcaldia</i>.
 */
@Entity
@Table(name = "alcaldias")
public class Alcaldia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlcaldia;

    @Column(name = "nombre")
    private String name;

    public Alcaldia() {
    }

    public Integer getIdAlcaldia() {
        return idAlcaldia;
    }

    public void setIdAlcaldia(Integer idAlcaldia) {
        this.idAlcaldia = idAlcaldia;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }
} // end of class domain
