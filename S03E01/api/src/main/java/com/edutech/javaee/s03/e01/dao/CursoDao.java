/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.CursoDto;
import com.edutech.javaee.s03.e01.model.Ciclo;
import com.edutech.javaee.s03.e01.model.Curso;
import com.edutech.javaee.s03.e01.model.Salon;
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
public class CursoDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final CicloDao clDao;
    final SalonDao slDao;

    public CursoDao() {
        this.clDao = null;
        this.slDao = null;
    }

    @Inject
    public CursoDao(CicloDao cicloDao, SalonDao salonDao) {
        this.clDao = cicloDao;
        this.slDao = salonDao;
    }

    private Ciclo findCiclo(Integer id) {
        Ciclo c = this.clDao.find(id);

        if (c == null) {
            throw new NullPointerException("No se puede encontrar el ciclo requerido: " + id);
        }

        return c;
    }

    private Salon findSalon(Integer id) {
        Salon s = this.slDao.find(id);

        if (s == null) {
            throw new NullPointerException("No se puede encontrar el salón requerido: " + id);
        }

        return s;
    }

    public Curso find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Curso u JOIN FETCH u.ciclo JOIN FETCH u.salon WHERE u.id = :parametro", Curso.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Curso> findAll() {
        return this.em
                .createQuery("SELECT u FROM Curso u JOIN FETCH u.ciclo JOIN FETCH u.salon", Curso.class)
                .getResultList();
    }

    public Curso save(CursoDto dto) {
        Curso c = new Curso();

        Ciclo ci = findCiclo(dto.getCiclo());
        Salon s = findSalon(dto.getSalon());

        c.setCiclo(ci);
        c.setSalon(s);

        c.setCodigo(dto.getCodigo());
        c.setDireccion(dto.getDireccion());

        this.em.persist(c);
        return c;
    }

    public Curso edit(CursoDto dto) {
        Curso c = this.find(dto.getId());

        if (c != null) {
            Ciclo ci = findCiclo(dto.getCiclo());
            Salon s = findSalon(dto.getSalon());

            c.setCiclo(ci);
            c.setSalon(s);

            c.setCodigo(dto.getCodigo());
            c.setDireccion(dto.getDireccion());
            this.em.merge(c);
        }
        return c;
    }

    public Curso remove(Integer id) {
        Curso ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
