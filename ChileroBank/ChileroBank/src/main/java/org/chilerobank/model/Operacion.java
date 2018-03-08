/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "operacion")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "operacion.findAll", query = "Select DISTINCT d from Operacion d JOIN FETCH d.transacciones")
    ,
    //JOIN FETCH
    @NamedQuery(name = "operacion.findById", query = "Select d from Operacion d JOIN FETCH d.transacciones WHERE d.id = :id")
})
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operacionGen")
    @SequenceGenerator(name = "operacionGen", sequenceName = "operacion_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacion", fetch = FetchType.LAZY)
    private List<Transaccion> transacciones;

    public Operacion() {
    }

    public Operacion(Integer id, String nombre, String descripcion, List<Transaccion> transacciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacciones = transacciones;
    }

    public Operacion(String nombre, String descripcion, List<Transaccion> transacciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.transacciones = transacciones;
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
     * @return the transacciones
     */
    @XmlTransient
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transacciones the transacciones to set
     */
    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

}
