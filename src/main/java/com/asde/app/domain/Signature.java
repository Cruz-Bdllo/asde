package com.asde.app.domain;

import javax.persistence.*;
import java.io.Serializable;

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

    public Signature(Integer idSignature, String name, String observations) {
        this.idSignature = idSignature;
        this.name = name;
        this.observations = observations;
    }

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
