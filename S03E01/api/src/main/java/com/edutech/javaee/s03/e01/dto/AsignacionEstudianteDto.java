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
public class AsignacionEstudianteDto {
    private Integer id;
    private Integer estudiante;
    private Integer curso;
    private float zona;
    private float examenFinal;
    private float notaFinal;

    public AsignacionEstudianteDto() {
    }

    public AsignacionEstudianteDto(Integer id, Integer estudiante, Integer curso, float zona, float examenFinal, float notaFinal) {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.zona = zona;
        this.examenFinal = examenFinal;
        this.notaFinal = notaFinal;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEstudiante() {
        return estudiante;
    }

    public Integer getCurso() {
        return curso;
    }

    public float getZona() {
        return zona;
    }

    public float getExamenFinal() {
        return examenFinal;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEstudiante(Integer estudiante) {
        this.estudiante = estudiante;
    }

    public void setCurso(Integer curso) {
        this.curso = curso;
    }

    public void setZona(float zona) {
        this.zona = zona;
    }

    public void setExamenFinal(float examenFinal) {
        this.examenFinal = examenFinal;
    }

    public void setNotaFinal(float notaFinal) {
        this.notaFinal = notaFinal;
    }
    
    
}
