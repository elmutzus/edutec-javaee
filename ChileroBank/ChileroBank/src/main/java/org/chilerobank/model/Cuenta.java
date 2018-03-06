/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "cuenta")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "cuenta.findAll", query = "select DISTINCT d from cuenta d JOIN FETCH d.cliente")
    ,
    //JOIN FETCH
    @NamedQuery(name = "cuenta.findById", query = "select d from cuenta d JOIN FETCH d.cliente WHERE d.id = :id")
})
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuentaGen")
    @SequenceGenerator(name = "cuentaGen", sequenceName = "cuenta_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @Column(name = "moneda")
    private String moneda;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_apertura")
    private Date fechaApertura;

    @Column(name = "estado")
    private Integer estado;

    @JoinColumn(name = "tipo_cuenta", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCuenta tipoCuenta;

    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

    @JoinColumn(name = "saldo", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<Saldo> saldos;

    @JoinColumn(name = "transaccion", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<Transaccion> transacciones;

    public Cuenta() {
    }

    public Cuenta(Integer id, String moneda, Date fechaApertura, Integer estado, TipoCuenta tipoCuenta, Cliente cliente, List<Saldo> saldos, List<Transaccion> transacciones) {
        this.id = id;
        this.moneda = moneda;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.tipoCuenta = tipoCuenta;
        this.cliente = cliente;
        this.saldos = saldos;
        this.transacciones = transacciones;
    }

    public Cuenta(String moneda, Date fechaApertura, Integer estado, TipoCuenta tipoCuenta, Cliente cliente, List<Saldo> saldos, List<Transaccion> transacciones) {
        this.moneda = moneda;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.tipoCuenta = tipoCuenta;
        this.cliente = cliente;
        this.saldos = saldos;
        this.transacciones = transacciones;
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
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the fechaApertura
     */
    public Date getFechaApertura() {
        return fechaApertura;
    }

    /**
     * @param fechaApertura the fechaApertura to set
     */
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the tipoCuenta
     */
    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the saldo
     */
    public List<Saldo> getSaldos() {
        return saldos;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldos(List<Saldo> saldos) {
        this.saldos = saldos;
    }

    /**
     * @return the transaccion
     */
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

}
