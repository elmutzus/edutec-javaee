/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.RolDto;
import com.edutech.javaee.s03.e01.model.Rol;
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
public class RolDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Rol find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Rol u WHERE u.id = :param", Rol.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Rol> findAll() {
        return this.em
                .createQuery("SELECT u FROM Rol u", Rol.class)
                .getResultList();
    }

    public Rol save(RolDto dto) {
        Rol r = new Rol();

        r.setDescripcion(dto.getDescripcion());
        r.setNombre(dto.getNombre());

        this.em.persist(r);
        return r;
    }

    public Rol edit(RolDto dto) {
        Rol r = this.find(dto.getId());

        if (r != null) {
            r.setDescripcion(dto.getDescripcion());
            r.setNombre(dto.getNombre());
            this.em.merge(r);
        }
        return r;
    }

    public Rol remove(Integer id) {
        Rol ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
