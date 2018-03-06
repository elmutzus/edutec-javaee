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
public class SaldoDto {

    private Integer id;
    private Integer cuenta;
    private Date fecha;

    public SaldoDto() {
    }

    public SaldoDto(Integer id, Integer cuenta, Date fecha) {
        this.id = id;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    public SaldoDto(Integer cuenta, Date fecha) {
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
