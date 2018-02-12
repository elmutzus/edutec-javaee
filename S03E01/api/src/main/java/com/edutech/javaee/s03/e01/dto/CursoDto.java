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
public class CursoDto {
    private Integer id;
    private String codigo;
    private String direccion;
    private Integer ciclo;
    private Integer salon;

    public CursoDto() {
    }

    public CursoDto(Integer id, String codigo, String direccion, Integer ciclo, Integer salon) {
        this.id = id;
        this.codigo = codigo;
        this.direccion = direccion;
        this.ciclo = ciclo;
        this.salon = salon;
    }

    public Integer getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public Integer getSalon() {
        return salon;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public void setSalon(Integer salon) {
        this.salon = salon;
    }
    
    
}
