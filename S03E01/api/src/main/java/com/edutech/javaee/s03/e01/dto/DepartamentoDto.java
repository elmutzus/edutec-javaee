/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dto;

import java.util.List;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class DepartamentoDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private List<Integer> municipios;

    public DepartamentoDto() {
    }

    public DepartamentoDto(Integer id, String codigo, String nombre, List<Integer> municipios) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.municipios = municipios;
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

    public List<Integer> getMunicipios() {
        return municipios;
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

    public void setMunicipios(List<Integer> municipios) {
        this.municipios = municipios;
    }
    
    
}
