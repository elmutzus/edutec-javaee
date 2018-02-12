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
public class MunicipioDto {
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer departamento;

    public MunicipioDto() {
    }

    public MunicipioDto(Integer id, String nombre, String codigo, Integer departamento) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public Integer getDepartamento(){
        return departamento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setDepartamento(Integer departamento){
        this.departamento = departamento;
    }
}
