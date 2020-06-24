package com.asde.app.domain;


import javax.persistence.*;
import java.io.Serializable;


/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>dependencias</b> en la BD, con el fin de poder
 * representar los datos de una <i>dependencia</i>.
 */
@Entity
@Table(name = "dependencias")
public class Dependence implements Serializable {
    private static long serialVersionUID=1L;

    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depen")
    private Integer idDependence;

    @Column(name = "nombre")
    private String name;


    /* ~    METHODS
    --------------------------------------------------- */
    public Dependence() {
    }

    public Dependence(Integer idDependence, String name) {
        this.idDependence = idDependence;
        this.name = name;
    }

    public Integer getIdDependence() {
        return idDependence;
    }

    public void setIdDependence(Integer idDependence) {
        this.idDependence = idDependence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} // end of class
