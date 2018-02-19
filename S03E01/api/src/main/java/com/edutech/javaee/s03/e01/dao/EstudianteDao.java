/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.EstudianteDto;
import com.edutech.javaee.s03.e01.model.Estudiante;
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
public class EstudianteDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Estudiante find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Estudiante u WHERE u.id = :param", Estudiante.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Estudiante> findAll() {
        return this.em
                .createQuery("SELECT u FROM Estudiante u", Estudiante.class)
                .getResultList();
    }

    public Estudiante save(EstudianteDto dto) {
        Estudiante e = new Estudiante();

        e.setCarnet(dto.getCarnet());
        e.setDireccion(dto.getDireccion());
        e.setNombre(dto.getNombre());

        this.em.persist(e);
        return e;
    }

    public Estudiante edit(EstudianteDto dto) {
        Estudiante e = this.find(dto.getId());

        if (e != null) {
            e.setCarnet(dto.getCarnet());
            e.setDireccion(dto.getDireccion());
            e.setNombre(dto.getNombre());
            this.em.merge(e);
        }
        return e;
    }

    public Estudiante remove(Integer id) {
        Estudiante ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
