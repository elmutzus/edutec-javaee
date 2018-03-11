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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "cuenta")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "cuenta.findAll", query = "SELECT DISTINCT cn FROM Cuenta cn LEFT JOIN FETCH cn.cliente")
    ,
    //JOIN FETCH
    @NamedQuery(name = "cuenta.findById", query = "SELECT cn FROM Cuenta cn LEFT JOIN FETCH cn.cliente WHERE cn.id = :id")
})
public class Cuenta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "moneda")
    private String moneda;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_apertura")
    private Date fechaApertura;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cuenta_id")
    private TipoCuenta tipoCuenta;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Saldo> saldos;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaccion> transacciones;

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
    @XmlTransient
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
    @XmlTransient
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
    @XmlTransient
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
    @XmlTransient
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
