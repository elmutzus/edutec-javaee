/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "saldo")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "saldo.findAll", query = "Select DISTINCT d from Saldo d LEFT JOIN FETCH d.cuenta")
    ,
    //JOIN FETCH
    @NamedQuery(name = "saldo.findById", query = "Select d from Saldo d LEFT JOIN FETCH d.cuenta WHERE d.id = :id")
})
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "cuenta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta cuenta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    public Saldo() {
    }

    public Saldo(Integer id, Cuenta cuenta, Date fecha) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the cuenta
     */
    @XmlTransient
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
