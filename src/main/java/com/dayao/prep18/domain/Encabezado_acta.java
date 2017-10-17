package com.dayao.prep18.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Encabezado_acta.
 */
@Entity
@Table(name = "encabezado_acta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Encabezado_acta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "distrito")
    private Integer distrito;

    @Column(name = "tipo_acta")
    private Integer tipo_acta;

    @Column(name = "seccion")
    private Integer seccion;

    @Column(name = "casilla")
    private String casilla;

    @Column(name = "digitalizacion")
    private String digitalizacion;

    @Column(name = "estatus")
    private Integer estatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistrito() {
        return distrito;
    }

    public Encabezado_acta distrito(Integer distrito) {
        this.distrito = distrito;
        return this;
    }

    public void setDistrito(Integer distrito) {
        this.distrito = distrito;
    }

    public Integer getTipo_acta() {
        return tipo_acta;
    }

    public Encabezado_acta tipo_acta(Integer tipo_acta) {
        this.tipo_acta = tipo_acta;
        return this;
    }

    public void setTipo_acta(Integer tipo_acta) {
        this.tipo_acta = tipo_acta;
    }

    public Integer getSeccion() {
        return seccion;
    }

    public Encabezado_acta seccion(Integer seccion) {
        this.seccion = seccion;
        return this;
    }

    public void setSeccion(Integer seccion) {
        this.seccion = seccion;
    }

    public String getCasilla() {
        return casilla;
    }

    public Encabezado_acta casilla(String casilla) {
        this.casilla = casilla;
        return this;
    }

    public void setCasilla(String casilla) {
        this.casilla = casilla;
    }

    public String getDigitalizacion() {
        return digitalizacion;
    }

    public Encabezado_acta digitalizacion(String digitalizacion) {
        this.digitalizacion = digitalizacion;
        return this;
    }

    public void setDigitalizacion(String digitalizacion) {
        this.digitalizacion = digitalizacion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public Encabezado_acta estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Encabezado_acta encabezado_acta = (Encabezado_acta) o;
        if (encabezado_acta.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), encabezado_acta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Encabezado_acta{" +
            "id=" + getId() +
            ", distrito='" + getDistrito() + "'" +
            ", tipo_acta='" + getTipo_acta() + "'" +
            ", seccion='" + getSeccion() + "'" +
            ", casilla='" + getCasilla() + "'" +
            ", digitalizacion='" + getDigitalizacion() + "'" +
            ", estatus='" + getEstatus() + "'" +
            "}";
    }
}
