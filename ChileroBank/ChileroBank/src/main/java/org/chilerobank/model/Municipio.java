/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "municipio")
@NamedQueries({
    // Distinct
    @NamedQuery(name = "municipio.findAll", query = "SELECT DISTINCT mn FROM Municipio mn LEFT JOIN FETCH mn.departamento LEFT JOIN FETCH mn.clientes")
    ,
    //JOIN FETCH
    @NamedQuery(name = "municipio.findById", query = "SELECT mn FROM Municipio mn JOIN FETCH mn.departamento LEFT JOIN FETCH mn.clientes WHERE mn.id = :id")
})
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipioGen")
    @SequenceGenerator(name = "municipioGen", sequenceName = "municipio_seq", initialValue = 10)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @JoinColumn(name = "departamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamento departamento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio", fetch = FetchType.LAZY)
    List<Cliente> clientes;

    public Municipio() {
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

    @XmlTransient
    public List<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
