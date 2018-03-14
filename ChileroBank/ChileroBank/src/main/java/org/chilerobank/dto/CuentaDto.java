/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class CuentaDto {

    private Integer id;
    private String moneda;
    private Date fechaApertura;
    private Integer estado;
    private Integer tipoCuenta;
    private Integer cliente;
    private List<Integer> transacciones;
    private Float monto;

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
    public Integer getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(Integer tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return the cliente
     */
    public Integer getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the transacciones
     */
    public List<Integer> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transacciones the transacciones to set
     */
    public void setTransacciones(List<Integer> transacciones) {
        this.transacciones = transacciones;
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

}
