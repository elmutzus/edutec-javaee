/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dto;

import java.util.Date;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class TransaccionDto {

    private Integer id;
    private Date fechaMovimiento;
    private Float monto;
    private Integer cuenta;
    private Integer operacion;

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
     * @return the operacion
     */
    public Integer getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

}
