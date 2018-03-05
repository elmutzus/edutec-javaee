/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGen")
    @SequenceGenerator(name = "usuarioGen", sequenceName = "usuario_seq", initialValue = 10)
    private Integer id;

    private String codigo;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String mimeType;
    private String nombre;
    private String nombreArchivo;
    private String password;
    private String telefono;

    @Transient
    private String fechaNacimientoConFormato;

    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Integer id, String codigo, String email, Date fechaNacimiento, String mimeType, String nombre, String nombreArchivo, String password, String telefono, String fechaNacimientoConFormato, Rol rol) {
        this.id = id;
        this.codigo = codigo;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.mimeType = mimeType;
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimientoConFormato = fechaNacimientoConFormato;
        this.rol = rol;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @return the fechaNacimientoConFormato
     */
    public String getFechaNacimientoConFormato() {
        return fechaNacimientoConFormato;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @param fechaNacimientoConFormato the fechaNacimientoConFormato to set
     */
    public void setFechaNacimientoConFormato(String fechaNacimientoConFormato) {
        this.fechaNacimientoConFormato = fechaNacimientoConFormato;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
