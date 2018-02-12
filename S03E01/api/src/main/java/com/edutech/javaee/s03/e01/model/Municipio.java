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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="MUNICIPIO")
public class Municipio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipioGen")
    @SequenceGenerator(name="municipioGen", sequenceName = "municipio_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    private String nombre;
    
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamento departamento;

    public Municipio() {
    }

    public Municipio(Integer id, String codigo, String nombre, Departamento departamento) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
    }
    
    public Municipio(String codigo, String nombre, Departamento departamento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
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

    @XmlTransient
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
        
}
