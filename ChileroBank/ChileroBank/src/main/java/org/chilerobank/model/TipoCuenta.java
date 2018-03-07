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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "tipo_cuenta")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "tipoCuenta.findAll", query = "select DISTINCT d from tipoCuenta d JOIN FETCH d.cuentas")
    ,
    //JOIN FETCH
    @NamedQuery(name = "tipoCuenta.findById", query = "select d from tipoCuenta d JOIN FETCH d.cuentas WHERE d.id = :id")
})
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoCuentaGen")
    @SequenceGenerator(name = "tipoCuentaGen", sequenceName = "tipoCuenta_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tasaInteres")
    private Float tasaInteres;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo_cuenta", fetch = FetchType.LAZY)
    private List<Cuenta> cuentas;

    public TipoCuenta() {
    }

    public TipoCuenta(Integer id, String nombre, String descripcion, Float tasaInteres, List<Cuenta> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tasaInteres = tasaInteres;
        this.cuentas = cuentas;
    }

    public TipoCuenta(String nombre, String descripcion, Float tasaInteres, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tasaInteres = tasaInteres;
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
     * @return the tasaInteres
     */
    public Float getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(Float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    /**
     * @return the cuentas
     */
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

}
