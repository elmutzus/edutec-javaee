/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "cliente.findAll", query = "SELECT DISTINCT cl FROM Cliente cl LEFT JOIN FETCH cl.cuentas")
    ,
    @NamedQuery(name = "cliente.findById", query = "SELECT cl FROM Cliente cl LEFT JOIN FETCH cl.cuentas WHERE cl.id = :id")
})
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "nit")
    private String nit;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio")
    private Municipio municipio;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    public Cliente() {
    }

    public Cliente(Integer id, String nombre, String direccion, String nit, Date fechaNacimiento, Municipio municipio, List<Cuenta> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.fechaNacimiento = fechaNacimiento;
        this.municipio = municipio;
        this.cuentas = cuentas;
    }
    
        public Cliente(String nombre, String direccion, String nit, Date fechaNacimiento, Municipio municipio, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.fechaNacimiento = fechaNacimiento;
        this.municipio = municipio;
        this.cuentas = cuentas;
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
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
     * @return the municipio
     */
    @XmlTransient
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Cuenta> getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
