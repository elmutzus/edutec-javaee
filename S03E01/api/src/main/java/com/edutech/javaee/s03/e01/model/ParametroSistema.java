package com.edutech.javaee.s03.e01.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="PARAMETRO_SISTEMA")
public class ParametroSistema implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parametroGen")
    @SequenceGenerator(name="parametroGen", sequenceName = "parametro_seq", initialValue = 10)
    private Integer id;

    private String nombre;

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
