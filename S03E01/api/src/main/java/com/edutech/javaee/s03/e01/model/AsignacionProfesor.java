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
@Table(name="ASIGNACION_PROFESOR")
public class AsignacionProfesor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asproGen")
    @SequenceGenerator(name="asproGen", sequenceName = "aspro_seq", initialValue = 10)
    private Integer id;

    @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesor profesor;

    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;

    public AsignacionProfesor() {
    }

    public AsignacionProfesor(Integer id, Profesor profesor, Curso curso) {
        this.id = id;
        this.profesor = profesor;
        this.curso = curso;
    }
    
    public AsignacionProfesor(Profesor profesor, Curso curso) {
        this.profesor = profesor;
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
