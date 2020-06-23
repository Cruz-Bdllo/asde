package com.asde.app.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ingresos")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Integer idIncome;

    @Column(name = "folio", unique = true)
    @NotNull(message = "El folio del acuse es necesario")
    @NotBlank(message = "El folio del acuse es necesario")
    private String codIncome;

    @Column(name = "direccion")
    private String address;

    @Column(name = "estado")
    private TYPE_STATUS status;

    @Column(name = "fecha_ingreso")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de ingreso es necesaria")
    private Date creatAt;

    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de entrega es necesaria")
    private Date endDate;

    @Column(name = "fecha_vigencia")
    @Temporal(TemporalType.DATE)
    private Date expirateDate;

    @Column(name = "vigente")
    private boolean expirate;

    @Column(name = "observaciones")
    private String observations;

    @OneToOne
    @JoinColumn(name = "id_alcaldia")
    @NotNull(message = "Debe seleccionar una alcaldia")
    private Alcaldia alcaldia;

    @OneToOne
    @JoinColumn(name = "id_empresa")
    @NotNull(message = "Debe seleccionar un cliente")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_tramite")
    @NotNull(message = "Debe indicar que tramite ingreso")
    private Procedure procedure;

    @Column(name = "entregado")
    private TYPE_DELIVERED delivered;


    public static enum TYPE_DELIVERED {
        NO_ENTREGADO, ENTREGADO;
    }

    public static enum TYPE_STATUS {
        INGRESADO, COMPLETADO, PREVENCION;
    }

    public Income() {
    }

    public Integer getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(Integer idIncome) {
        this.idIncome = idIncome;
    }

    public String getCodIncome() {
        return codIncome;
    }

    public void setCodIncome(String codIncome) {
        this.codIncome = codIncome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TYPE_STATUS getStatus() {
        return status;
    }

    public void setStatus(TYPE_STATUS status) {
        this.status = status;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) throws ParseException {
//        SimpleDateFormat parseador = new SimpleDateFormat("YYYY-MM-DD");
        this.creatAt = creatAt;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getExpirateDate() {
        return expirateDate;
    }

    public void setExpirateDate(Date expirateDate) {
        this.expirateDate = expirateDate;
    }

    public boolean isExpirate() {
        return expirate;
    }

    public void setExpirate(boolean expirate) {
        this.expirate = expirate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Alcaldia getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(Alcaldia alcaldia) {
        this.alcaldia = alcaldia;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public TYPE_DELIVERED getDelivered() {
        return delivered;
    }

    public void setDelivered(TYPE_DELIVERED delivered) {
        this.delivered = delivered;
    }
} // end of class domain
