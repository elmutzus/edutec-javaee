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
public class ClienteDto {

    private Integer id;
    private String nombre;
    private String direccion;
    private String nit;
    private Date fechaNacimiento;
    private Integer municipio;
    private List<Integer> cuentas;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the municipio
     */
    public Integer getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the cuentas
     */
    public List<Integer> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(List<Integer> cuentas) {
        this.cuentas = cuentas;
    }

}
