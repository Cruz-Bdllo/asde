package com.asde.app.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>firmas</b> en la BD, con el fin de poder
 * representar las <i>firmas</i> que requiere un trámite para poder ser ingresado.
 */
@Entity
@Table(name = "firmas")
public class Signature implements Serializable {
    private static long serialVersionUID = 1L;

    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_firma")
    private Integer idSignature;

    @Column(name = "nombre")
    private String name;

    @Column(name = "observaciones")
    private String observations;


    /* ~    METHODS
    --------------------------------------------------- */
    public Signature(){}

    public Integer getIdSignature() {
        return idSignature;
    }

    public void setIdSignature(Integer idSignature) {
        this.idSignature = idSignature;
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
