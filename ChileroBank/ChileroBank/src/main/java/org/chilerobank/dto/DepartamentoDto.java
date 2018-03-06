/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dto;

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

    public DepartamentoDto(String codigo, String nombre, List<Integer> municipios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.municipios = municipios;
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the municipios
     */
    public List<Integer> getMunicipios() {
        return municipios;
    }

    /**
     * @param municipios the municipios to set
     */
    public void setMunicipios(List<Integer> municipios) {
        this.municipios = municipios;
    }

}
