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
public class ProfesorDto {
    private Integer id;
    private String carnet;
    private String nombre;
    private String direccion;

    public ProfesorDto() {
    }

    public ProfesorDto(Integer id, String carnet, String nombre, String direccion) {
        this.id = id;
        this.carnet = carnet;
        this.nombre = nombre;
        this.direccion = direccion;
    }
 
    public Integer getId() {
        return id;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
