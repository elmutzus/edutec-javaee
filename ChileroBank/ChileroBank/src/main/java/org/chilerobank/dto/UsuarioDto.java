/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dto;

import java.util.Date;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class UsuarioDto {

    private Integer id;
    private Integer rol;
    private String codigo;
    private String email;
    private Date fechaNacimiento;
    private String nombre;
    private String password;
    private String telefono;

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, Integer rol, String codigo, String email, Date fechaNacimiento, String nombre, String password, String telefono) {
        this.id = id;
        this.rol = rol;
        this.codigo = codigo;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
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
     * @return the rol
     */
    public Integer getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Integer rol) {
        this.rol = rol;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
