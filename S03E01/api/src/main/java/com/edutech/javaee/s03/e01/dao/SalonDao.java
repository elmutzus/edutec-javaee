/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.SalonDto;
import com.edutech.javaee.s03.e01.model.Salon;
import com.edutech.javaee.s03.e01.model.Sede;
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
public class SalonDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final SedeDao sdDao;

    public SalonDao() {
        this.sdDao = null;
    }

    @Inject
    public SalonDao(SedeDao sedeDao) {
        this.sdDao = sedeDao;
    }

    private Sede findSede(Integer id) {
        Sede s = this.sdDao.find(id);

        if (s == null) {
            throw new NullPointerException("No se puede encontrar la sede requerida: " + id);
        }

        return s;
    }

    public Salon find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Salon u JOIN FETCH u.sede WHERE u.id = :parametro", Salon.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Salon> findAll() {
        return this.em
                .createQuery("SELECT u FROM Salon u JOIN FETCH u.sede", Salon.class)
                .getResultList();
    }

    public Salon save(SalonDto dto) {
        Salon s = new Salon();
        Sede sede = findSede(dto.getSede());

        s.setSede(sede);
        s.setCodigo(dto.getCodigo());

        this.em.persist(s);
        return s;
    }

    public Salon edit(SalonDto dto) {
        Salon s = this.find(dto.getId());

        if (s != null) {
            Sede sede = findSede(dto.getSede());

            s.setSede(sede);
            s.setCodigo(dto.getCodigo());
            this.em.merge(s);
        }
        return s;
    }

    public Salon remove(Integer id) {
        Salon ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
