package com.asde.app.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>requisitos</b> en la BD, con el fin de poder
 * representar los <i>requisitos</i> que requiere un trámite para poder ser ingresado.
 */
@Entity
@Table(name = "requisitos")
public class Requirement implements Serializable {
    private static long serialVersionUID = 1L;

    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito")
    private Integer idRequirement;

    @Column(name = "nombre")
    private String name;

    @Column(name = "observaciones")
    private String observations;


    /* ~    METHODS
    --------------------------------------------------- */
    public Requirement(){}

    public Integer getIdRequirement() {
        return idRequirement;
    }

    public void setIdRequirement(Integer idRequirement) {
        this.idRequirement = idRequirement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
} // end of class
