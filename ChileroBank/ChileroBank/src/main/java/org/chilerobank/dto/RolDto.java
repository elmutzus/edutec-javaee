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
public class RolDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private List<Integer> usuarios;

    public RolDto() {
    }

    public RolDto(Integer id, String nombre, String descripcion, List<Integer> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios = usuarios;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the usuarios
     */
    public List<Integer> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Integer> usuarios) {
        this.usuarios = usuarios;
    }

}
