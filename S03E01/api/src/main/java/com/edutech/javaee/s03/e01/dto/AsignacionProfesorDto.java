/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dto;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class AsignacionProfesorDto {
    private Integer id;
    private Integer profesor;
    private Integer curso;

    public AsignacionProfesorDto() {
    }

    public AsignacionProfesorDto(Integer id, Integer profesor, Integer curso) {
        this.id = id;
        this.profesor = profesor;
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProfesor() {
        return profesor;
    }

    public Integer getCurso() {
        return curso;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProfesor(Integer profesor) {
        this.profesor = profesor;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }
}
