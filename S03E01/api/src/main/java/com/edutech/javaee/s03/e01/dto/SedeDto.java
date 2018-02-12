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
public class SedeDto {
    private Integer id;    
    private String codigo;
    private String nombre;
    private String direccion;
    private Integer municipio;

    public SedeDto() {
    }

    public SedeDto(Integer id, String codigo, String nombre, String direccion, Integer municipio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
    }

    public Integer getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }
    
    
}
