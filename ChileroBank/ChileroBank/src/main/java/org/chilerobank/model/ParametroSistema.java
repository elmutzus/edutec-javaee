/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Entity
@Table(name = "parametro_sistema")
@NamedQueries({
    @NamedQuery(name = "parametroSistema.findAll", query = "Select DISTINCT d from ParametroSistema d")
    ,
    @NamedQuery(name = "parametroSistema.findById", query = "Select d from ParametroSistema d WHERE d.id = :id")
        ,
    @NamedQuery(name = "parametroSistema.findByName", query = "Select d from ParametroSistema d WHERE d.nombre = :name")
})
public class ParametroSistema implements Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valor")
    private String valor;

    public ParametroSistema() {
    }

    public ParametroSistema(Integer id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
