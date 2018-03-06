/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dto;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class MunicipioDto {

    private Integer id;
    private String codigo;
    private String nombre;
    private Integer departamento;

    public MunicipioDto() {
    }

    public MunicipioDto(Integer id, String codigo, String nombre, Integer departamento) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public MunicipioDto(String codigo, String nombre, Integer departamento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
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
     * @return the departamento
     */
    public Integer getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

}
