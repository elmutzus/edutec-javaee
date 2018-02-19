/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.AsignacionProfesorDto;
import com.edutech.javaee.s03.e01.model.AsignacionProfesor;
import com.edutech.javaee.s03.e01.model.Curso;
import com.edutech.javaee.s03.e01.model.Profesor;
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
public class AsignacionProfesorDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final CursoDao crDao;
    final ProfesorDao prfDao;

    public AsignacionProfesorDao() {
        this.crDao = null;
        this.prfDao = null;
    }

    @Inject
    public AsignacionProfesorDao(CursoDao cursoDao, ProfesorDao profesorDao) {
        this.crDao = cursoDao;
        this.prfDao = profesorDao;
    }

    private Curso findCurso(Integer id) {
        Curso c = this.crDao.find(id);

        if (c == null) {
            throw new NullPointerException("No se puede encontrar el curso requerido: " + id);
        }

        return c;
    }

    private Profesor findProfesor(Integer id) {
        Profesor p = this.prfDao.find(id);

        if (p == null) {
            throw new NullPointerException("No se puede encontrar el profesor requerido: " + id);
        }

        return p;
    }

    public AsignacionProfesor find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM AsignacionProfesor u JOIN FETCH u.profesor JOIN FETCH u.curso WHERE u.id = :parametro", AsignacionProfesor.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<AsignacionProfesor> findAll() {
        return this.em
                .createQuery("SELECT u FROM AsignacionProfesor u JOIN FETCH u.profesor JOIN FETCH u.curso", AsignacionProfesor.class)
                .getResultList();
    }

    public AsignacionProfesor save(AsignacionProfesorDto dto) {
        AsignacionProfesor ap = new AsignacionProfesor();

        Curso c = findCurso(dto.getCurso());
        Profesor p = findProfesor(dto.getProfesor());

        ap.setCurso(c);
        ap.setProfesor(p);

        this.em.persist(ap);
        return ap;
    }

    public AsignacionProfesor edit(AsignacionProfesorDto dto) {
        AsignacionProfesor ap = this.find(dto.getId());
        if (ap != null) {
            Curso c = findCurso(dto.getCurso());
            Profesor p = findProfesor(dto.getProfesor());

            ap.setCurso(c);
            ap.setProfesor(p);
            this.em.merge(ap);
        }
        return ap;
    }

    public AsignacionProfesor remove(Integer id) {
        AsignacionProfesor ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
