/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.AsignacionEstudianteDto;
import com.edutech.javaee.s03.e01.model.AsignacionEstudiante;
import com.edutech.javaee.s03.e01.model.Curso;
import com.edutech.javaee.s03.e01.model.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
public class AsignacionEstudianteDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final CursoDao crDao;
    final EstudianteDao estDao;

    public AsignacionEstudianteDao() {
        this.crDao = null;
        this.estDao = null;
    }

    @Inject
    public AsignacionEstudianteDao(CursoDao cursoDao, EstudianteDao estudianteDao) {
        this.crDao = cursoDao;
        this.estDao = estudianteDao;
    }

    private Curso findCurso(Integer id) {
        Curso c = this.crDao.find(id);

        if (c == null) {
            throw new NullPointerException("No se puede encontrar el curso requerido: " + id);
        }

        return c;
    }

    private Estudiante findEstudiante(Integer id) {
        Estudiante e = this.estDao.find(id);

        if (e == null) {
            throw new NullPointerException("No se puede encontrar el estudiante requerido: " + id);
        }

        return e;
    }

    public AsignacionEstudiante find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM AsignacionEstudiante u JOIN FETCH u.estudiante JOIN FETCH u.curso WHERE u.id = :parametro", AsignacionEstudiante.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<AsignacionEstudiante> findAll() {
        return this.em
                .createQuery("SELECT u FROM AsignacionEstudiante u JOIN FETCH u.estudiante JOIN FETCH u.curso", AsignacionEstudiante.class)
                .getResultList();
    }

    public AsignacionEstudiante save(AsignacionEstudianteDto dto) {
        AsignacionEstudiante ae = new AsignacionEstudiante();

        Curso c = findCurso(dto.getCurso());
        Estudiante e = findEstudiante(dto.getEstudiante());

        ae.setCurso(c);
        ae.setEstudiante(e);
        ae.setExamenFinal(dto.getExamenFinal());
        ae.setNotaFinal(dto.getNotaFinal());
        ae.setZona(dto.getZona());

        this.em.persist(ae);
        return ae;
    }

    public AsignacionEstudiante edit(AsignacionEstudianteDto dto) {
        AsignacionEstudiante ae = this.find(dto.getId());
        if (ae != null) {
            Curso c = findCurso(dto.getCurso());
            Estudiante e = findEstudiante(dto.getEstudiante());

            ae.setCurso(c);
            ae.setEstudiante(e);
            ae.setExamenFinal(dto.getExamenFinal());
            ae.setNotaFinal(dto.getNotaFinal());
            ae.setZona(dto.getZona());

            this.em.merge(ae);
        }
        return ae;
    }

    public AsignacionEstudiante remove(Integer id) {
        AsignacionEstudiante ae = this.find(id);
        this.em.remove(ae);
        return ae;
    }
}
