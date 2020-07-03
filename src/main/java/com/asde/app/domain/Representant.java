package com.asde.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>representantes</b> en la BD, con el fin de poder
 * representar los datos de los <b>representantes</b> legales permitidos por una empresa cliente.
 */
@Entity
@Table(name = "representantes")
public class Representant implements Serializable {
    private static long serialVersionUID = 123L;

    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repre")
    private Integer idRepresentant;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre del representante no puede quedar vacio")
    private String name;

    @Column(name = "apaterno")
    @NotBlank(message = "El apellido paterno del representante no puede quedar vacio")
    private String firstName;

    @Column(name = "amaterno")
    @NotBlank(message = "El apellido materno del representante no puede quedar vacio")
    private String lastName;

    @Column(name = "correo")
    @NotBlank(message = "Debe registrar un correo valido")
    @Email(message = "Debe registrar un correo valido")
    private String email;

    @Column(name = "telefono")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Client client;


    /* ~    METHODS
    --------------------------------------------------- */
    public Representant() {
    }

    public Integer getIdRepresentant() {
        return idRepresentant;
    }

    public void setIdRepresentant(Integer idRepresentant) {
        this.idRepresentant = idRepresentant;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString(){
        return String.format("%s %s %s", name, firstName, lastName);
    }
}
