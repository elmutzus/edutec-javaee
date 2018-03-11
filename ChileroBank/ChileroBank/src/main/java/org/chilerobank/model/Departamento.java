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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "departamento")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT DISTINCT d FROM Departamento d LEFT JOIN FETCH d.municipios")
    ,
    @NamedQuery(name = "Departamento.findById", query = "SELECT d FROM Departamento d LEFT JOIN FETCH d.municipios WHERE d.id = :idDepartamento")
    ,
    @NamedQuery(name = "Departamento.findByName", query = "SELECT d FROM Departamento d LEFT JOIN FETCH d.municipios WHERE d.nombre = :nameDepartamento")
})
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Municipio> municipios;

    public Departamento() {
    }

    public Departamento(Integer id, String codigo, String nombre, List<Municipio> mns) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.municipios = mns;
    }

    public Departamento(String codigo, String nombre, List<Municipio> mns) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.municipios = mns;
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

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
