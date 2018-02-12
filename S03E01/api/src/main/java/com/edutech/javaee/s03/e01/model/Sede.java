package com.edutech.javaee.s03.e01.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="SEDE")
public class Sede implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sedeGen")
    @SequenceGenerator(name="sedeGen", sequenceName = "sede_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    private String nombre;
    private String direccion;
    
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Municipio municipio;

    public Sede() {
    }

    public Sede(Integer id, String codigo, String nombre, String direccion, Municipio municipio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
    }
    
    public Sede(String codigo, String nombre, String direccion, Municipio municipio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

        
}
