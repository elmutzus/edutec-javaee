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
@Table(name="SALON")
public class Salon implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salonGen")
    @SequenceGenerator(name="salonGen", sequenceName = "salon_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    
    @JoinColumn(name = "ID_SEDE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sede sede;

    public Salon() {
    }

    public Salon(Integer id, String codigo, Sede sede) {
        this.id = id;
        this.codigo = codigo;
        this.sede = sede;
    }
    
    public Salon(String codigo, Sede sede) {
        this.codigo = codigo;
        this.sede = sede;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }



        
}
