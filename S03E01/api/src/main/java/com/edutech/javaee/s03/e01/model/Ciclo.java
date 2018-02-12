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
@Table(name="CICLO")
public class Ciclo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cicloGen")
    @SequenceGenerator(name="cicloGen", sequenceName = "ciclo_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    
    public Ciclo() {
    }

    public Ciclo(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
    
    public Ciclo(String codigo) {
        this.id = id;
        this.codigo = codigo;
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
        
}
