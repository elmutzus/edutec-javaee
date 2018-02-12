/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dto;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class SalonDto {
    private Integer id;
    private String codigo;
    private Integer sede;

    public SalonDto() {
    }

    public SalonDto(Integer id, String codigo, Integer sede) {
        this.id = id;
        this.codigo = codigo;
        this.sede = sede;
    }

    public Integer getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getSede() {
        return sede;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setSede(Integer sede) {
        this.sede = sede;
    }   
    
}
