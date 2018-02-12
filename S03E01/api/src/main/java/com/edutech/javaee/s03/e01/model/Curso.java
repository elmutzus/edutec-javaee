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
@Table(name="CURSO")
public class Curso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cursoGen")
    @SequenceGenerator(name="cursoGen", sequenceName = "curso_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    private String direccion;
    
    @JoinColumn(name = "ID_CICLO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ciclo ciclo;

    @JoinColumn(name = "ID_SALON", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Salon salon;

    public Curso() {
    }

    public Curso(Integer id, String codigo, String direccion, Ciclo ciclo, Salon salon) {
        this.id = id;
        this.codigo = codigo;
        this.direccion = direccion;
        this.ciclo = ciclo;
        this.salon = salon;
    }
    
    public Curso(String codigo, String direccion, Ciclo ciclo, Salon salon) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.ciclo = ciclo;
        this.salon = salon;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }


}
