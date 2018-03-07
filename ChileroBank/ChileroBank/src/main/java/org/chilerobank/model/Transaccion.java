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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "transaccion.findAll", query = "Select DISTINCT d from Transaccion d JOIN FETCH d.cuenta")
    ,
    //JOIN FETCH
    @NamedQuery(name = "transaccion.findById", query = "Select d from Transaccion d JOIN FETCH d.cuenta WHERE d.id = :id")
})
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaccionGen")
    @SequenceGenerator(name = "transaccionGen", sequenceName = "transaccion_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Column(name = "monto")
    private Float monto;

    @JoinColumn(name = "cuenta", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta cuenta;

    @JoinColumn(name = "operacion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Operacion operacion;

    public Transaccion() {
    }

    public Transaccion(Integer id, Date fechaMovimiento, Float monto, Cuenta cuenta, Operacion operacion) {
        this.id = id;
        this.fechaMovimiento = fechaMovimiento;
        this.monto = monto;
        this.cuenta = cuenta;
        this.operacion = operacion;
    }

    public Transaccion(Date fechaMovimiento, Float monto, Cuenta cuenta, Operacion operacion) {
        this.fechaMovimiento = fechaMovimiento;
        this.monto = monto;
        this.cuenta = cuenta;
        this.operacion = operacion;
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
     * @return the fechaMovimiento
     */
    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * @param fechaMovimiento the fechaMovimiento to set
     */
    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    /**
     * @return the monto
     */
    public Float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Float monto) {
        this.monto = monto;
    }

    /**
     * @return the cuenta
     */
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
     * @return the operacion
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

}
