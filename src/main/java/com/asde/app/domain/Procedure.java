package com.asde.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tramites")
public class Procedure implements Serializable {
    private static long serialVersionUID = 1L;

    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramite")
    private Integer idProcedure;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre del trámite no debe estar vacio")
    @NotNull
    private String name;

    @Column(name = "vigencia")
    @NotBlank(message = "La vigencia del trámite no debe quedar vacio")
    @Size(min = 2, message = "Debe seleccionar una fecha valida")
    private String expiration;

    @Column(name = "tipo")
    private TYPE_P type;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_depen")
    @NotNull(message = "Debe seleccionar la dependencia a la que pertenece el trámite")
    private Dependence dependence;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tramite_req",
            joinColumns = {@JoinColumn(name = "id_tramite")},
            inverseJoinColumns = {@JoinColumn(name = "id_requisito")})
    @Size(min = 1, message = "Al menos debe seleccionar un requisito")
    private List<Requirement> requirements;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "tramite_firmas",
            joinColumns = {@JoinColumn(name = "id_tramite")},
            inverseJoinColumns = {@JoinColumn(name = "id_firma")})
    private List<Signature> signatures;

    public static enum TYPE_P {
        NORMAL, ESTRUCTURAL
    }


    /* ~    METHODS
    --------------------------------------------------- */
    public Procedure(){
        this.requirements=new ArrayList<>();
        this.signatures=new ArrayList<>();
    }

    public Procedure(Integer idProcedure, String name, String expiration, TYPE_P type, Dependence dependence) {
        this.requirements=new ArrayList<>();
        this.signatures=new ArrayList<>();
        this.idProcedure = idProcedure;
        this.name = name;
        this.expiration = expiration;
        this.type = type;
        this.dependence = dependence;
    }

    public Integer getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(Integer idProcedure) {
        this.idProcedure = idProcedure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public TYPE_P getType() {
        return type;
    }

    public void setType(TYPE_P type) {
        this.type = type;
    }

    public Dependence getDependence() {
        return dependence;
    }

    public void setDependence(Dependence dependence) {
        this.dependence = dependence;
    }

    public void addRequiriment(Requirement requirement) {
        this.requirements.add(requirement);
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public void addSignature (Signature signature) {
        this.signatures.add(signature);
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<Signature> signatures) {
        this.signatures = signatures;
    }

} // end of class
