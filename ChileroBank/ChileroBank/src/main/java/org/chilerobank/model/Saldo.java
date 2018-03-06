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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "saldo")
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saldoGen")
    @SequenceGenerator(name = "saldoGen", sequenceName = "saldo_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "cuenta", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Integer cuenta;

    @Column(name = "fecha")
    private Date fecha;

    public Saldo() {
    }

    public Saldo(Integer id, Integer cuenta, Date fecha) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    public Saldo(Integer cuenta, Date fecha) {
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
    public Integer getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Integer cuenta) {
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
