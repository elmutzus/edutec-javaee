/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.ProfesorDto;
import com.edutech.javaee.s03.e01.model.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
public class ProfesorDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Profesor find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Profesor u WHERE u.id = :param", Profesor.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Profesor> findAll() {
        return this.em
                .createQuery("SELECT u FROM Profesor u", Profesor.class)
                .getResultList();
    }

    public Profesor save(ProfesorDto dto) {
        Profesor p = new Profesor();

        p.setCarnet(dto.getCarnet());
        p.setDireccion(dto.getDireccion());
        p.setNombre(dto.getNombre());

        this.em.persist(p);
        return p;
    }

    public Profesor edit(ProfesorDto dto) {
        Profesor p = this.find(dto.getId());

        if (p != null) {
            p.setCarnet(dto.getCarnet());
            p.setDireccion(dto.getDireccion());
            p.setNombre(dto.getNombre());
            this.em.merge(p);
        }
        return p;
    }

    public Profesor remove(Integer id) {
        Profesor ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
