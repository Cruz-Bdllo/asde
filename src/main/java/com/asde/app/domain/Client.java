package com.asde.app.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que permite mapear sus propiedades a los campos de la tabla <b>empresa</b> en la BD, con el fin de poder
 * representar los datos de los <i>clientes</i>.
 */
@Entity
@Table(name = "empresa")
public class Client {
    private static long serialVersion = 123L;
    /* ~    PROPERTIES
    --------------------------------------------------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;


    @Column(name = "rfc")
    @NotNull(message = "No puede quedar vacio el RFC")
    @NotBlank(message = "No puede quedar vacio el RFC")
    @Size(min = 10, max = 13, message = "El RFC debe contener entre 10 y 13 caracteres")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rfc;

    @Column(name = "razon_social")
    @NotNull(message = "No puede quedar vacio la razón social")
    @NotBlank(message = "No puede quedar vacio la razón social")
    private String name;

    @Column(name = "correo")
    @Email(message = "El campo correo debe tener un formato adecuado (puede que le falte @ o .)")
    @NotBlank(message = "El campo correo debe ser llenado y tener un formato adecuado (incluya @ o .)")
    private String email;

    @Column(name = "telefono")
    @NotNull(message = "El campo del teléfono no puede quedar vacio")
    @NotBlank(message = "El campo del teléfono no puede quedar vacio")
    private String phone;

    @Column(name = "celular")

    private String cellphone;


    @Column(name = "direccion")
    private String address;

    @Column(name = "activo")
    private ACTIVE_T active;

    @Column(name = "create_at")
    private Date createAt;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Representant> representants;


    public static enum ACTIVE_T {
        INACTIVO, ACTIVO
    }


    @PrePersist
    public void setCreate() { this.createAt = new Date(); }

    /* ~    METHODS
    --------------------------------------------------- */
    public Client() {
        this.representants = new ArrayList<>();
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ACTIVE_T getActive() {
        return active;
    }

    public void setActive(ACTIVE_T active) {
        this.active = active;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Representant> getRepresentants() {
        return representants;
    }

    public void setRepresentants(List<Representant> representants) {
        this.representants = representants;
    }

    public void addRepresentant (Representant representant) {
        this.representants.add(representant);
    }
} // end of domain
